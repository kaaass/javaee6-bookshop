package net.kaaass.bookshop.promote;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.kaaass.bookshop.controller.request.OrderCreateMultiRequest;
import net.kaaass.bookshop.controller.request.OrderCreateSingleRequest;
import net.kaaass.bookshop.dto.ProductDto;
import net.kaaass.bookshop.exception.NotFoundException;
import net.kaaass.bookshop.mapper.ProductMapper;
import net.kaaass.bookshop.mapper.PromoteMapper;
import net.kaaass.bookshop.service.OrderRequestContext;
import net.kaaass.bookshop.service.ProductService;
import net.kaaass.bookshop.service.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;

@Stateless
@Slf4j
public class OrderPromoteContextFactory {

    @EJB
    private UserService userService;

    @EJB
    private ProductService productService;

    @Inject
    private PromoteMapper promoteMapper;

    @Inject
    private ProductMapper productMapper;

    /**
     * 从请求中建立订单打折上下文
     */
    public OrderPromoteContext buildFromRequestContext(OrderRequestContext requestContext) throws NotFoundException {
        val result = new OrderPromoteContext();
        val request = requestContext.getRequest();
        val products = new ArrayList<PromoteItem>();
        // 商品
        if (request instanceof OrderCreateMultiRequest) {
            for (val cartItem : ((OrderCreateMultiRequest) request).getCachedCartItems()) {
                val item = promoteMapper.cartDtoToPromoteItem(cartItem);
                item.setPrice(item.getProduct().getPrice());
                products.add(item);
            }
        } else if (request instanceof OrderCreateSingleRequest) {
            val entity = productService.getEntityById(((OrderCreateSingleRequest) request).getProductId());
            val item = new PromoteItem();
            item.setProduct(productMapper.productEntityToDto(entity));
            item.setCount(1);
            item.setPrice(item.getProduct().getPrice());
            products.add(item);
        } else {
            throw new NotFoundException("未知订单请求");
        }
        result.setProducts(products);
        // 价格
        result.setPrice(0);
        result.setMailPrice(0);
        // 用户id
        result.setUid(requestContext.getUid());
        // 地址
        result.setAddress(userService.getAddressById(request.getAddressId()));
        return result;
    }

    public OrderPromoteContext buildFromSingleProduct(ProductDto product, int count, String uid, String addressId) throws NotFoundException {
        val result = new OrderPromoteContext();
        val products = new ArrayList<PromoteItem>();
        // 请求所有打折
        if (count <= 0) {
            result.setForView(true);
            count = 1;
        }
        // 商品
        val item = new PromoteItem();
        item.setProduct(product);
        item.setCount(count);
        item.setPrice(product.getPrice());
        products.add(item);
        result.setProducts(products);
        // 价格
        result.setPrice(0);
        result.setMailPrice(0);
        // 用户id
        result.setUid(uid);
        // 地址
        if (addressId != null) {
            result.setAddress(userService.getAddressById(addressId));
        }
        return result;
    }
}
