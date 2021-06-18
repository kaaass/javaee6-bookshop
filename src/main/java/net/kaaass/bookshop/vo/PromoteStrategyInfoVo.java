package net.kaaass.bookshop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.kaaass.bookshop.dto.PromoteStyle;
import net.kaaass.bookshop.util.PromoteStyleSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 打折策略信息 VO
 *
 * @author kaaass
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromoteStrategyInfoVo {

    private String id;

    private String name;

    private String hint;

    @JsonSerialize(using = PromoteStyleSerializer.class)
    private PromoteStyle style;
}
