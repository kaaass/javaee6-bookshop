package net.kaaass.bookshop.exception;

import lombok.Getter;
import net.kaaass.bookshop.util.StatusEnum;

/**
 * 无权请求错误
 */
@Getter
public class ForbiddenException extends BaseException {
    StatusEnum status = StatusEnum.FORBIDDEN;

    public ForbiddenException(String message) {
        super(message);
    }
}