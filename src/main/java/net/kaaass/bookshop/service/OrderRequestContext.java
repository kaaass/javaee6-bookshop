package net.kaaass.bookshop.service;

import lombok.Data;
import net.kaaass.bookshop.controller.request.OrderCreateRequest;

/**
 * 订单请求 DTO
 * @author kaaass
 */
@Data
public class OrderRequestContext {

    private String requestId;

    private String uid;

    private OrderCreateRequest request;
}
