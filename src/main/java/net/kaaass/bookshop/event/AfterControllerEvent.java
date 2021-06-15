package net.kaaass.bookshop.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.kaaass.bookshop.eventhandle.Event;

/**
 * Controller返回后事件
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AfterControllerEvent extends Event {

    private Object controllerResult;
}
