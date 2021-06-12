package net.kaaass.bookshop.conf;

import net.kaaass.bookshop.controller.response.GlobalResponse;
import net.kaaass.bookshop.exception.BaseException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * 程序异常响应处理
 * @author kaaass
 */
@Provider
public class AppExceptionHandler implements ExceptionMapper<BaseException> {
    @Override
    public Response toResponse(BaseException exception) {
        return GlobalResponse.fail(exception).toResponse();
    }
}
