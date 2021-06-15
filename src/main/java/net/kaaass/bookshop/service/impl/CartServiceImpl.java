package net.kaaass.bookshop.service.impl;

import java8.util.function.Function;
import java8.util.function.Supplier;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import net.kaaass.bookshop.controller.request.CartAddRequest;
import net.kaaass.bookshop.dao.Pageable;
import net.kaaass.bookshop.dao.entity.CartEntity;
import net.kaaass.bookshop.dao.repository.CartRepository;
import net.kaaass.bookshop.dto.CartDto;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.exception.BaseException;
import net.kaaass.bookshop.exception.ForbiddenException;
import net.kaaass.bookshop.exception.NotFoundException;
import net.kaaass.bookshop.mapper.ProductMapper;
import net.kaaass.bookshop.service.CartService;
import net.kaaass.bookshop.service.ProductService;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * TODO 购物车服务
 * @author kaaass
 */
@Stateful
@Slf4j
public class CartServiceImpl implements CartService, Serializable {

    @Inject
    private CartRepository cartRepository;

    @Inject
    private ProductService productService;

    @Override
    public CartEntity getEntityById(String id) throws NotFoundException {
        return cartRepository.findById(id)
                .orElseThrow(BaseException.supplier(NotFoundException.class, "该项目不在购物车内！"));
    }

    @Override
    public CartEntity getEntityByIdAndCheck(String id, String uid) throws NotFoundException, ForbiddenException {
        val result = this.getEntityById(id);
        if (!result.getUid().equals(uid)) {
            throw new ForbiddenException("该项目不在购物车内！");
        }
        return result;
    }

    @Override
    public CartDto addToCart(final String uid, CartAddRequest request) throws NotFoundException, BadRequestException {
        val product = productService.getEntityById(request.getProductId());
        val entity = cartRepository.findByProductAndUid(product, uid)
                        .orElseGet(new Supplier<CartEntity>() {
                            @Override
                            public CartEntity get() {
                                val newEntity = new CartEntity();
                                newEntity.setUid(uid);
                                newEntity.setProduct(product);
                                newEntity.setCount(0);
                                return newEntity;
                            }
                        });
        // 检查购买限制
        val limit = product.getBuyLimit();
        val dest = entity.getCount() + request.getCount();
        if (limit != -1 && dest > limit) {
            throw new BadRequestException(String.format("本商品限购%d件！", limit));
        } else {
            entity.setCount(dest);
        }
        return ProductMapper.INSTANCE.cartEntityToDto(cartRepository.save(entity));
    }

    @Override
    public void removeFromCart(String uid, String id) throws NotFoundException, ForbiddenException {
        val entity = this.getEntityByIdAndCheck(id, uid);
        cartRepository.delete(entity);
    }

    @Override
    public CartDto modifyItemCount(String uid, String id, int count) throws NotFoundException, ForbiddenException, BadRequestException {
        val entity = this.getEntityByIdAndCheck(id, uid);
        val product = entity.getProduct();
        // 检查购买限制
        val limit = product.getBuyLimit();
        if (limit != -1 && count > limit) {
            throw new BadRequestException(String.format("本商品限购%d件！", limit));
        } else {
            entity.setCount(count);
        }
        return ProductMapper.INSTANCE.cartEntityToDto(cartRepository.save(entity));
    }

    @Override
    public List<CartDto> getAllByUid(String uid, Pageable pageable) {
        return StreamSupport.stream(cartRepository.findAllByUidOrderByCreateTimeDesc(uid, pageable))
                .map(new Function<CartEntity, CartDto>() {
                    @Override
                    public CartDto apply(CartEntity entity) {
                        return ProductMapper.INSTANCE.cartEntityToDto(entity);
                    }
                })
                .collect(Collectors.<CartDto>toList());
    }
}
