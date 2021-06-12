package net.kaaass.bookshop.exception;

import java8.util.function.Supplier;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.val;
import net.kaaass.bookshop.util.StatusEnum;

import java.lang.reflect.InvocationTargetException;

/**
 * 坏请求错误
 */
@Getter
public class BadRequestException extends BaseException {
    StatusEnum status = StatusEnum.BAD_REQUEST;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}