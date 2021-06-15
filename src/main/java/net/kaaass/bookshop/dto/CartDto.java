package net.kaaass.bookshop.dto;

import lombok.Data;
import net.kaaass.bookshop.util.DateToLongSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * 购物车项目 DTO
 * @author kaaass
 */
@Data
public class CartDto {
    private String id;

    private String uid;

    private ProductDto product;

    private int count;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;
}
