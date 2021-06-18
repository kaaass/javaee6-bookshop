package bookshop.controller.response;

import bookshop.exception.BaseException;
import bookshop.util.StatusEnum;

import javax.ws.rs.core.Response;

/**
 * 标准返回格式
 *
 * @param <T> 返回类型
 */
public class GlobalResponse<T> {
    private int status;

    private String message;

    private T data;

    public GlobalResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

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

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GlobalResponse)) return false;
        final GlobalResponse<?> other = (GlobalResponse<?>) o;
        if (!other.canEqual(this)) return false;
        if (this.getStatus() != other.getStatus()) return false;
        final Object this$message = this.getMessage();
        final Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) return false;
        final Object this$data = this.getData();
        final Object other$data = other.getData();
        return this$data == null ? other$data == null : this$data.equals(other$data);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GlobalResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getStatus();
        final Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        final Object $data = this.getData();
        result = result * PRIME + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        return "GlobalResponse(status=" + this.getStatus() + ", message=" + this.getMessage() + ", data=" + this.getData() + ")";
    }
}
