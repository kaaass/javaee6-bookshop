package net.kaaass.bookshop.exception;

import net.kaaass.bookshop.util.StatusEnum;

/**
 * 未找到错误
 */
public class NotFoundException extends BaseException {
    StatusEnum status = StatusEnum.NOT_FOUND;

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public StatusEnum getStatus() {
        return this.status;
    }
}
