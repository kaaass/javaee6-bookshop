package net.kaaass.bookshop.exception;

import java8.util.function.Supplier;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.val;
import net.kaaass.bookshop.util.StatusEnum;

import java.lang.reflect.InvocationTargetException;

/**
 * 通用错误类
 * <p>
 * 记录状态码
 */
@Getter
public class BaseException extends Exception {
    StatusEnum status = StatusEnum.SUCCESS;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public static <T extends BaseException> Supplier<T> supplier(Class<T> clazz, final String message) {
        try {
            val cons = clazz.getConstructor(String.class);
            return new Supplier<T>() {
                @SneakyThrows
                @Override
                public T get() {
                    try {
                        return cons.newInstance(message);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new InternalErrorExeption("未知", e);
                    }
                }
            };
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static <T extends BaseException> Supplier<T> supplier(Class<T> clazz, final String message, final Throwable cause) {
        try {
            val cons = clazz.getConstructor(String.class, Throwable.class);
            return new Supplier<T>() {
                @SneakyThrows
                @Override
                public T get() {
                    try {
                        return cons.newInstance(message, cause);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new InternalErrorExeption("未知", e);
                    }
                }
            };
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
