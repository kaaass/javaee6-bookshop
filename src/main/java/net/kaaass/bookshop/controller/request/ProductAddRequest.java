package net.kaaass.bookshop.controller.request;

import lombok.Data;
import net.kaaass.bookshop.constraints.Isbn;
import net.kaaass.bookshop.constraints.Uuid;
import net.kaaass.bookshop.util.DateToLongSerializer;
import net.kaaass.bookshop.util.LongToDateDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class ProductAddRequest {

    @Size(
            message = "商品名称长度应该5~50之间",
            min = 5,
            max = 50
    )
    private String name;

    @Uuid(message = "缩略图格式错误")
    private String thumbnailId;

    @Min(value = 0, message = "价格不能为负")
    private float price;

    @Min(value = 0, message = "价格不能为负")
    private float mailPrice;

    @Min(value = -1, message = "购买限制最小为-1")
    private int buyLimit;

    @Isbn(message = "ISBN格式错误")
    private String isbn;

    @Size(
            message = "作者名称长度应该2~100之间",
            min = 2,
            max = 100
    )
    private String author;

    @Min(value = -1, message = "首页展示顺序最小为-1")
    private int indexOrder;

    @JsonDeserialize(using = LongToDateDeserializer.class)
    private Date publishDate;

    @Uuid(message = "分类格式错误")
    private String categoryId;

    @JsonDeserialize(using = LongToDateDeserializer.class)
    private Date startSellTime;

    @Min(value = 0, message = "库存不能为负")
    private int rest;
}
