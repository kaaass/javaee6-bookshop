package net.kaaass.bookshop.dto;

import lombok.Data;
import net.kaaass.bookshop.util.DateToLongSerializer;
import net.kaaass.bookshop.util.LongToDateDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
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

    private String isbn;

    private String author;

    @JsonDeserialize(using = LongToDateDeserializer.class)
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date publishDate;

    private int indexOrder;

    private ProductStorageDto storage;

    @JsonDeserialize(using = LongToDateDeserializer.class)
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date startSellTime;
}
