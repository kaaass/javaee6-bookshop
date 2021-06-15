package net.kaaass.bookshop.conf;

import lombok.extern.slf4j.Slf4j;
import net.kaaass.bookshop.controller.response.GlobalResponse;
import net.kaaass.bookshop.util.StatusEnum;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * 其他异常响应处理
 * @author kaaass
 */
@Provider
@Slf4j
public class CommonExceptionHandler implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        log.warn("未知错误", exception);
        return GlobalResponse
                .fail(StatusEnum.INTERNAL_ERROR,
                        String.format("%s: %s", exception.getClass().getName(), exception.getLocalizedMessage()))
                .toResponse();
    }
}
