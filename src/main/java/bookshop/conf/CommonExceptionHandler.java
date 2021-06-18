package bookshop.conf;

import bookshop.controller.response.GlobalResponse;
import bookshop.util.StatusEnum;
import org.slf4j.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * 其他异常响应处理
 *
 * @author kaaass
 */
@Provider
public class CommonExceptionHandler implements ExceptionMapper<Exception> {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CommonExceptionHandler.class);

    @Override
    public Response toResponse(Exception exception) {
        log.warn("未知错误", exception);
        return GlobalResponse
                .fail(StatusEnum.INTERNAL_ERROR,
                        String.format("%s: %s", exception.getClass().getName(), exception.getLocalizedMessage()))
                .toResponse();
    }
}
