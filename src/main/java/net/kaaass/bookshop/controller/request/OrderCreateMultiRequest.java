package net.kaaass.bookshop.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.kaaass.bookshop.constraints.Uuid;
import net.kaaass.bookshop.dto.CartDto;

import java.util.List;

/**
 * 创建多商品订单请求
 * @author kaaass
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderCreateMultiRequest extends OrderCreateRequest {

    public final static String TYPE = "MULTI";

    @Data
    public static class CartItem {

        @Uuid(message = "id格式不正确")
        private String id;
    }

    private List<CartItem> cartItems;

    private List<CartDto> cachedCartItems = null;
}
