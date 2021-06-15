package net.kaaass.bookshop.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.kaaass.bookshop.eventhandle.Cancelable;
import net.kaaass.bookshop.eventhandle.Event;
import net.kaaass.bookshop.promote.OrderPromoteResult;

/**
 * 订单打折后事件
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Cancelable
public class AfterOrderPromoteEvent extends Event {

    private OrderPromoteResult promoteResult;
}
