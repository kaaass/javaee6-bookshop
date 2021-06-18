package net.kaaass.bookshop.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.kaaass.bookshop.dto.UserAuthDto;
import net.kaaass.bookshop.eventhandle.Event;

/**
 * 用户注册事件
 * <p>
 * 值修改将会被忽略
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegisterEvent extends Event {

    UserAuthDto userAuth;
}
