package net.kaaass.bookshop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.kaaass.bookshop.util.DateToLongSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

@Data
@AllArgsConstructor
public class AuthTokenVo {
    String token;

    @JsonSerialize(using = DateToLongSerializer.class)
    Date publishTime;
}
