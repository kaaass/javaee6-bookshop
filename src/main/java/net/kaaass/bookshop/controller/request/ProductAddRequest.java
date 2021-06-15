package net.kaaass.bookshop.controller.request;

import lombok.Data;
import net.kaaass.bookshop.constraints.Uuid;
import net.kaaass.bookshop.util.LongToDateDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import javax.validation.constraints.Digits;
import java.util.Date;

@Data
public class ProductAddRequest {

    private String name;

    @Uuid(message = "thumbnailId格式错误")
    private String thumbnailId;

    private float price;

    private float mailPrice;

    private int buyLimit;

    @Uuid(message = "categoryId格式错误")
    private String categoryId;

    @JsonDeserialize(using = LongToDateDeserializer.class)
    private Date startSellTime;

    private int rest;
}
