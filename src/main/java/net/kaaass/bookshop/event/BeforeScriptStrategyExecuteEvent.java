package net.kaaass.bookshop.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.kaaass.bookshop.eventhandle.Event;
import net.kaaass.bookshop.promote.OrderPromoteContext;
import net.kaaass.bookshop.promote.strategy.dbms.JavascriptStrategy;
import net.kaaass.bookshop.vo.PromoteStrategyInfoVo;

/**
 * JavaScript策略执行前事件
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BeforeScriptStrategyExecuteEvent extends Event {

    private OrderPromoteContext context;

    private PromoteStrategyInfoVo strategyInfo;

    private JavascriptStrategy.Param param;

    private Object extraInfo;
}
