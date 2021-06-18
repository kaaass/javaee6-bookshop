package net.kaaass.bookshop.promote;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.kaaass.bookshop.util.NumericUtils;

import java.util.List;

/**
 * 打折流执行器
 */
@Slf4j
public class PromoteExecutor {

    private final List<IPromoteStrategy> strategies;

    private final IPromoteCollector collector;


    PromoteExecutor(List<IPromoteStrategy> strategies, IPromoteCollector collector) {
        this.strategies = strategies;
        this.collector = collector;
    }

    /**
     * 执行打折流的打折计算
     */
    public OrderPromoteResult execute(OrderPromoteContext context) {
        OrderPromoteContext currentContext = context;
        val acceptType = collector.getInfoType();
        for (val strategy : strategies) {
            val result = strategy.doPromote(currentContext);
            if (acceptType.lessEq(result.resultType)) {
                currentContext = result.getContext();
                currentContext.setPrice(NumericUtils.moneyRound(currentContext.getPrice()));
                currentContext.setMailPrice(NumericUtils.moneyRound(currentContext.getMailPrice()));
                currentContext.getPromotes().add(strategy.getPromoteInfo()); // 添加打折信息
            }
            if (IPromoteStrategy.ResultType.NOT_MATCH.lessEq(result.resultType)) {
                log.info("打折策略：{}；打折结果: {}", strategy.getPromoteInfo().getName(), result);
            }
        }
        return collector.collect(currentContext);
    }
}
