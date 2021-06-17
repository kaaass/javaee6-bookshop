package net.kaaass.bookshop.util;

import java8.util.function.Function;
import java8.util.function.Predicate;
import java8.util.stream.StreamSupport;

import java.util.Arrays;

/**
 * 错误号
 *
 * @author kaaass
 */
public enum StatusEnum {
    /**
     * 成功
     */
    SUCCESS(200, ""), // 成功则不返回message段

    BAD_REQUEST(400, "请求错误"),
    FORBIDDEN(403, "请登录后访问"),
    NOT_FOUND(404, "未找到相关信息"),

    INTERNAL_ERROR(500, "服务器内部错误"),
    SERVICE_UNAVAILIBLE(503, "服务器暂不可用");

    private final int code;
    private final String description;

    StatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public boolean isSuccess() {
        return code == StatusEnum.SUCCESS.code;
    }

    public static String descriptionFor(final int code) {
        return StreamSupport.stream(Arrays.asList(values()))
                .filter(new Predicate<StatusEnum>() {
                    @Override
                    public boolean test(StatusEnum cur) {
                        return cur.code == code;
                    }
                })
                .map(new Function<StatusEnum, String>() {
                    @Override
                    public String apply(StatusEnum cur) {
                        return cur.description;
                    }
                })
                .findAny().orElse(Constants.UNKNOWN);
    }

    public int getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }
}
