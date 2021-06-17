package net.kaaass.bookshop.exception;

import net.kaaass.bookshop.util.StatusEnum;

/**
 * 无权请求错误
 */
public class ForbiddenException extends BaseException {
    StatusEnum status = StatusEnum.FORBIDDEN;

    public ForbiddenException(String message) {
        super(message);
    }

    public StatusEnum getStatus() {
        return this.status;
    }
}