package net.kaaass.bookshop.controller.request;

import lombok.Data;

/**
 * 购物车添加请求
 *
 * @author kaaass
 */
@Data
public class CartAddRequest {

    String productId;

    int count;
}
