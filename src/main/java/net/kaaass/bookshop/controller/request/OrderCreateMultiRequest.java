package net.kaaass.bookshop.controller.request;

import lombok.Data;

import java.util.List;

/**
 * 创建多商品订单请求
 * @author kaaass
 */
@Data
public class OrderCreateMultiRequest extends OrderCreateRequest {

    public final static String TYPE = "MULTI";

    @Data
    public static class CartItem {
        private String id;
    }

    private List<CartItem> cartItems;
}
