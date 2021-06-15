package net.kaaass.bookshop.controller.request;

import lombok.Data;
import net.kaaass.bookshop.util.LongToDateDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import java.util.Date;

@Data
public class ProductAddRequest {

    private String name;

    private String thumbnailId;

    private float price;

    private float mailPrice;

    private int buyLimit;

    private String categoryId;

    @JsonDeserialize(using = LongToDateDeserializer.class)
    private Date startSellTime;

    private int rest;
}
