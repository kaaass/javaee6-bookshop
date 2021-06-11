package net.kaaass.bookshop.conf;

import net.kaaass.bookshop.controller.response.GlobalResponse;
import net.kaaass.bookshop.exception.BaseException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * 异常响应处理
 * @author kaaass
 */
@Provider
public class ExceptionHandler implements ExceptionMapper<BaseException> {
    @Override
    public Response toResponse(BaseException exception) {
        return Response
                .ok()
                // .status(Response.Status.fromStatusCode(status.getCode()))
                .entity(GlobalResponse.fail(exception.getStatus(), exception.getMessage()))
                .build();
    }
}
