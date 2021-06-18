package net.kaaass.bookshop.dto;

import lombok.Data;
import net.kaaass.bookshop.util.DateToLongSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * 分类 DTO
 *
 * @author kaaass
 */
@Data
public class CategoryDto {
    private String id;

    private String name;

    private CategoryDto parent;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;
}
