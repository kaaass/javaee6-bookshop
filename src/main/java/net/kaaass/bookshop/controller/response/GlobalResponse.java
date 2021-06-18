package net.kaaass.bookshop.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import net.kaaass.bookshop.exception.BaseException;
import net.kaaass.bookshop.util.StatusEnum;

import javax.ws.rs.core.Response;

/**
 * 标准返回格式
 *
 * @param <T> 返回类型
 */
@Data
@ToString
@AllArgsConstructor
public class GlobalResponse<T> {
    private int status;

    private String message;

    private T data;

    public Response toResponse() {
        return Response
                .ok()
                // .status(Response.Status.fromStatusCode(status.getCode()))
                .entity(this)
                .build();
    }

    /**
     * 成功返回
     *
     * @param data 返回数据
     * @param <T>  返回数据类型
     * @return 返回VO
     */
    public static <T> GlobalResponse<T> success(T data) {
        return new GlobalResponse<>(StatusEnum.SUCCESS.getCode(), null, data);
    }

    /**
     * 失败返回
     *
     * @param status  状态码
     * @param message 错误信息
     * @param <T>     返回数据类型
     * @return 返回VO
     */
    public static <T> GlobalResponse<T> fail(StatusEnum status, String message) {
        return new GlobalResponse<>(status.getCode(), message == null ? status.getDescription() : message, null);
    }

    /**
     * 失败返回
     *
     * @param status 状态码
     * @param <T>    返回数据类型
     * @return 返回VO
     */
    public static <T> GlobalResponse<T> fail(StatusEnum status) {
        return fail(status, null);
    }

    /**
     * 失败返回
     *
     * @param exception 异常
     * @param <T>       返回数据类型
     * @return 返回VO
     */
    public static <T> GlobalResponse<T> fail(BaseException exception) {
        return fail(exception.getStatus(), exception.getMessage());
    }
}
