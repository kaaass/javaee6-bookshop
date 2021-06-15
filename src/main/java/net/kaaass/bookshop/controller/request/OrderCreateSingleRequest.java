package net.kaaass.bookshop.controller.request;

import lombok.Data;

/**
 * 创建单商品订单请求
 * @author kaaass
 */
@Data
public class OrderCreateSingleRequest extends OrderCreateRequest {

    public final static String TYPE = "SINGLE";

    private String productId;
}
