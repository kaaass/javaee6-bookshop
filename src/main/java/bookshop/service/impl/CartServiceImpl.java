package bookshop.service.impl;

import java8.util.Optional;
import java8.util.function.Supplier;
import bookshop.controller.request.CartAddRequest;
import bookshop.dao.Pageable;
import bookshop.dao.entity.ProductEntity;
import bookshop.dto.CartDto;
import bookshop.dto.ProductDto;
import bookshop.exception.BadRequestException;
import bookshop.exception.BaseException;
import bookshop.exception.NotFoundException;
import bookshop.mapper.ProductMapper;
import bookshop.service.CartService;
import bookshop.service.ProductService;
import bookshop.util.StringUtils;
import org.slf4j.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.*;

/**
 * 购物车服务
 *
 * @author kaaass
 */
@SessionScoped
@Stateful
public class CartServiceImpl implements CartService, Serializable {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CartServiceImpl.class);
    @Inject
    private ProductService productService;

    @Inject
    private ProductMapper productMapper;

    @Override
    public CartDto getById(String id) throws NotFoundException {
        return findById(id)
                .orElseThrow(BaseException.supplier(NotFoundException.class, "该项目不在购物车内！"));
    }

    @Override
    public CartDto addToCart(CartAddRequest request) throws NotFoundException, BadRequestException {
        final ProductEntity product = productService.getEntityById(request.getProductId());
        final CartDto entity = findByProduct(product)
                .orElseGet(new Supplier<CartDto>() {
                    @Override
                    public CartDto get() {
                        final CartDto newEntity = new CartDto();
                        newEntity.setProduct(productMapper.productEntityToDto(product));
                        newEntity.setCount(0);
                        return newEntity;
                    }
                });
        // 检查购买限制
        final int limit = product.getBuyLimit();
        final int dest = entity.getCount() + request.getCount();
        if (limit != -1 && dest > limit) {
            throw new BadRequestException(String.format("本商品限购%d件！", limit));
        } else {
            entity.setCount(dest);
        }
        return save(entity);
    }

    @Override
    public void removeFromCart(String id) throws NotFoundException {
        final CartDto entity = this.getById(id);
        delete(entity);
    }

    @Override
    public CartDto modifyItemCount(String id, int count) throws NotFoundException, BadRequestException {
        final CartDto entity = this.getById(id);
        final ProductDto product = entity.getProduct();
        // 检查购买限制
        final int limit = product.getBuyLimit();
        if (limit != -1 && count > limit) {
            throw new BadRequestException(String.format("本商品限购%d件！", limit));
        } else {
            entity.setCount(count);
        }
        return save(entity);
    }

    @Override
    public List<CartDto> getAllPerUser(Pageable pageable) {
        return new ArrayList<>(findAll());
    }

    @Override
    public void deleteById(String id) {
        this.cartEntities.remove(id);
    }

    /*
        购物车数据相关
     */

    private final Map<String, CartDto> cartEntities = new HashMap<>();

    private Optional<CartDto> findById(String id) {
        return Optional.ofNullable(this.cartEntities.get(id));
    }

    private Optional<CartDto> findByProduct(ProductEntity product) {
        if (product == null) {
            return Optional.empty();
        }
        final String pid = product.getId();
        for (final CartDto entity : findAll()) {
            if (pid.equals(entity.getProduct().getId())) {
                return Optional.of(entity);
            }
        }
        return Optional.empty();
    }

    private CartDto save(CartDto entity) {
        if (entity.getId() == null) {
            entity.setId(StringUtils.uuid());
        }
        this.cartEntities.put(entity.getId(), entity);
        return entity;
    }

    private void delete(CartDto entity) {
        this.cartEntities.remove(entity.getId());
    }

    private Collection<CartDto> findAll() {
        return this.cartEntities.values();
    }
}