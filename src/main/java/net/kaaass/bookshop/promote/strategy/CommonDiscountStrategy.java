package net.kaaass.bookshop.promote.strategy;

import lombok.AllArgsConstructor;
import lombok.val;
import net.kaaass.bookshop.dto.PromoteStyle;
import net.kaaass.bookshop.promote.IPromoteStrategy;
import net.kaaass.bookshop.promote.OrderPromoteContext;
import net.kaaass.bookshop.service.metadata.MetadataManager;
import net.kaaass.bookshop.util.Constants;
import net.kaaass.bookshop.util.NumericUtils;
import net.kaaass.bookshop.vo.PromoteStrategyInfoVo;

/**
 * 商品正常打折策略
 * <p>
 * 将会从商品元数据读入打折信息，若互斥，需要指定DisableExtra TODO
 */
@AllArgsConstructor
public class CommonDiscountStrategy implements IPromoteStrategy<OrderPromoteContext, OrderPromoteContext> {

    private final MetadataManager metadataManager;

    @Override
    public Result<OrderPromoteContext> doPromote(OrderPromoteContext context) {
        float allPrice = 0;
        val products = context.getProducts();
        float price = 0;
        boolean discounted = false;
        for (val item : products) {
            val discount = metadataManager.getForProduct(item.getProduct().getId(), Constants.METAKEY_DISCOUNT, "");
            price = item.getPrice();
            if (discount.length() > 0) {
                price = NumericUtils.moneyRound(price * Float.parseFloat(discount));
                item.setPrice(price);
                discounted = true;
            }
            allPrice += price;
        }
        if (!discounted) {
            return new Result<>(ResultType.NOT_MATCH); // 没打折
        } else {
            context.setPrice(allPrice);
            return new Result<>(ResultType.OK, context); // 打折了
        }
    }

    @Override
    public PromoteStrategyInfoVo getPromoteInfo() {
        return new PromoteStrategyInfoVo(null,
                "商品折扣",
                "该商品正在打折促销",
                PromoteStyle.GREAT);
    }
}
