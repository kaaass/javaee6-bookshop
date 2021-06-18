package net.kaaass.bookshop.promote.strategy;

import net.kaaass.bookshop.promote.IPromoteStrategy;
import net.kaaass.bookshop.promote.OrderPromoteContext;
import net.kaaass.bookshop.vo.PromoteStrategyInfoVo;

/**
 * 单位策略
 */
public class IdStrategy implements IPromoteStrategy<OrderPromoteContext, OrderPromoteContext> {
    @Override
    public Result<OrderPromoteContext> doPromote(OrderPromoteContext context) {
        return new Result<>(ResultType.PLACEHOLDER, context);
    }

    /**
     * 不应该被运行
     *
     * @return
     */
    @Override
    public PromoteStrategyInfoVo getPromoteInfo() {
        return null;
    }
}
