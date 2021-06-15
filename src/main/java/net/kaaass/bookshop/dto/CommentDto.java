package net.kaaass.bookshop.dto;

import lombok.Data;
import net.kaaass.bookshop.util.DateToLongSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * 评论 DTO
 * @author kaaass
 */
@Data
public class CommentDto {

    private String id;

    private String uid;

    private String orderId;

    private String productId;

    private int rate;

    private String content;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date commentTime;
}
