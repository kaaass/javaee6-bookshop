package bookshop.exception;

import bookshop.util.StatusEnum;

/**
 * 坏请求错误
 */
public class BadRequestException extends BaseException {
    StatusEnum status = StatusEnum.BAD_REQUEST;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public StatusEnum getStatus() {
        return this.status;
    }
}