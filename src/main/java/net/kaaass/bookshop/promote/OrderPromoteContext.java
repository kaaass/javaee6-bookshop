package net.kaaass.bookshop.promote;

import lombok.Data;
import net.kaaass.bookshop.dto.UserAddressDto;
import net.kaaass.bookshop.vo.PromoteStrategyInfoVo;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderPromoteContext {

    private float price;

    private float mailPrice;

    private List<PromoteItem> products;

    private String extra = "";

    /**
     * 记录打折情况
     */
    private List<PromoteStrategyInfoVo> promotes = new ArrayList<>();

    private String uid;

    private UserAddressDto address;

    private boolean forView = false;
}
