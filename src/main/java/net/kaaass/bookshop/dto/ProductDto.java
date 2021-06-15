package net.kaaass.bookshop.dto;

import lombok.Data;
import net.kaaass.bookshop.util.DateToLongSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * 商品 DTO
 * @author kaaass
 */
@Data
public class ProductDto {
    private String id;

    private String name;

    private MediaDto thumbnail;

    private float price;

    private float mailPrice;

    private int buyLimit;

    private CategoryDto category;

    private int indexOrder;

    private ProductStorageDto storage;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date startSellTime;
}
