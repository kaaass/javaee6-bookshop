package net.kaaass.bookshop.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.kaaass.bookshop.constraints.Uuid;

/**
 * 创建单商品订单请求
 *
 * @author kaaass
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderCreateSingleRequest extends OrderCreateRequest {

    public final static String TYPE = "SINGLE";

    @Uuid(message = "productId格式不正确")
    private String productId;
}
