package net.kaaass.bookshop.dto;

import lombok.Data;

/**
 * 商品元数据 DTO
 *
 * @author kaaass
 */
@Data
public class ProductMetadataDto {
    private String id;
    private String productId;
    private String key;
    private String value;
}
