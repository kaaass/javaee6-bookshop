package net.kaaass.bookshop.vo;

import lombok.Data;
import net.kaaass.bookshop.dto.MediaDto;

import java.util.List;

/**
 * 订单额外信息 VO
 * @author kaaass
 */
@Data
public class ProductExtraVo {

    private int monthPurchase;

    private String detail;

    private List<MediaDto> images;
}
