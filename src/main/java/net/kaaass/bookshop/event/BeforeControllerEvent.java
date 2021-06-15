package net.kaaass.bookshop.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.kaaass.bookshop.eventhandle.Cancelable;
import net.kaaass.bookshop.eventhandle.Event;

/**
 * Controller返回前事件
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Cancelable
public class BeforeControllerEvent extends Event {

    private Object[] args;
}
