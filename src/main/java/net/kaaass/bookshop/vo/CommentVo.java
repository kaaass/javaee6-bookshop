package net.kaaass.bookshop.vo;

import lombok.Data;
import net.kaaass.bookshop.util.DateToLongSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

@Data
public class CommentVo {

    private String id;

    private int rate;

    private String content;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date commentTime;
}
