package net.kaaass.bookshop.dto;

import lombok.Data;

/**
 * 订单内容 DTO
 * @author kaaass
 */
@Data
public class OrderItemDto {

    private String id;

    private ProductDto product;

    private float price;

    private int count;
}
