package net.kaaass.bookshop.promote.strategy;

import lombok.AllArgsConstructor;
import lombok.val;
import net.kaaass.bookshop.dto.PromoteStyle;
import net.kaaass.bookshop.promote.IPromoteStrategy;
import net.kaaass.bookshop.promote.OrderPromoteContext;
import net.kaaass.bookshop.vo.PromoteStrategyInfoVo;

/**
 * 商品正常包邮策略
 * <p>
 * 如果有包邮商品，则包邮，若无则取最大值
 * 可以通过extra添加规则NoCommonMailFreeStrategy来禁止 TODO
 */
@AllArgsConstructor
public class CommonMailFreeStrategy implements IPromoteStrategy<OrderPromoteContext, OrderPromoteContext> {

    public static CommonMailFreeStrategy INSTANCE = new CommonMailFreeStrategy();

    @Override
    public Result<OrderPromoteContext> doPromote(OrderPromoteContext context) {
        val products = context.getProducts();
        for (val item : products) {
            if (item.getProduct().getMailPrice() == 0F) {
                context.setMailPrice(0F);
            }
        }
        return new Result<>(context.getMailPrice() == 0F ? ResultType.OK : ResultType.NOT_MATCH, context);
    }

    @Override
    public PromoteStrategyInfoVo getPromoteInfo() {
        return new PromoteStrategyInfoVo(null,
                "商品包邮",
                "该商品享受包邮特权",
                PromoteStyle.NORMAL);
    }
}
