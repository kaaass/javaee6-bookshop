package net.kaaass.bookshop.dto;

import lombok.Data;
import net.kaaass.bookshop.util.DateToLongSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * 插件 DTO
 *
 * @author kaaass
 */
@Data
public class PluginDto {

    private String id;

    private String filename;

    private boolean enable = true;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date enableTime;
}
