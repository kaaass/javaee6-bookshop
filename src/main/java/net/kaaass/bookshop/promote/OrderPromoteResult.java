package net.kaaass.bookshop.promote;

import lombok.Data;
import net.kaaass.bookshop.dto.OrderItemDto;
import net.kaaass.bookshop.vo.PromoteStrategyInfoVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单打折结果
 * @author kaaass
 */
@Data
public class OrderPromoteResult {

    private float price;

    private float mailPrice;

    private List<OrderItemDto> products = new ArrayList<>();

    private List<PromoteStrategyInfoVo> promotes;
}
