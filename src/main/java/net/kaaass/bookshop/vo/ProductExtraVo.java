package net.kaaass.bookshop.vo;

import lombok.Data;
import net.kaaass.bookshop.dto.MediaDto;
import net.kaaass.bookshop.promote.OrderPromoteResult;

import java.util.List;

/**
 * 订单额外信息 VO
 * @author kaaass
 */
@Data
public class ProductExtraVo {

    private int monthPurchase;

    private String detail;

    private OrderPromoteResult promotes;

    private List<MediaDto> images;
}
