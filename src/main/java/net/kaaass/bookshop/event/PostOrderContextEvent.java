package net.kaaass.bookshop.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.kaaass.bookshop.eventhandle.Event;
import net.kaaass.bookshop.service.OrderRequestContext;

/**
 * 订单信息投送队列前事件
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostOrderContextEvent extends Event {

    private String uid;

    private OrderRequestContext context;
}
