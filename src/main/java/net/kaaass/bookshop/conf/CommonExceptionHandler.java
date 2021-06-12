package net.kaaass.bookshop.conf;

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
public class CommonExceptionHandler implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        return GlobalResponse
                .fail(StatusEnum.INTERNAL_ERROR,
                        String.format("%s: %s", exception.getClass().getName(), exception.getLocalizedMessage()))
                .toResponse();
    }
}
