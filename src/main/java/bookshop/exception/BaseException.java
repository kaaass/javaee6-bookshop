package bookshop.exception;

import java8.util.function.Supplier;
import bookshop.util.StatusEnum;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 通用错误类
 * <p>
 * 记录状态码
 */
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
            final Constructor<T> cons = clazz.getConstructor(String.class);
            return new Supplier<T>() {
                @Override
                public T get() {
                    try {
                        return cons.newInstance(message);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException ignored) {
                        return null;
                    }
                }
            };
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static <T extends BaseException> Supplier<T> supplier(Class<T> clazz, final String message, final Throwable cause) {
        try {
            final Constructor<T> cons = clazz.getConstructor(String.class, Throwable.class);
            return new Supplier<T>() {
                @Override
                public T get() {
                    try {
                        return cons.newInstance(message, cause);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException ignored) {
                        return null;
                    }
                }
            };
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public StatusEnum getStatus() {
        return this.status;
    }
}
