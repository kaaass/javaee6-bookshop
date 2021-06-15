package net.kaaass.bookshop.dto;

import lombok.Data;
import net.kaaass.bookshop.util.DateToLongSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

@Data
public class PromoteStrategyDto {

    private String id;

    private String name;

    private String hint;

    private String clazz;

    private String param;

    private int order;

    private PromoteStyle style;

    private boolean enabled = true;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date lastUpdateTime;
}
