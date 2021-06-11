package net.kaaass.bookshop.exception;

import net.kaaass.bookshop.util.StatusEnum;

/**
 * 服务器内部错误
 */
public class InternalErrorExeption extends BaseException {

    StatusEnum status = StatusEnum.INTERNAL_ERROR;

    public InternalErrorExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
