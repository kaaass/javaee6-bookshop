package net.kaaass.bookshop.dto;

import lombok.Data;
import net.kaaass.bookshop.util.DateToLongSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

@Data
public class MediaDto {
    private String id;

    private String type;

    private String url;

    private String uploaderUid;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date uploadTime;
}
