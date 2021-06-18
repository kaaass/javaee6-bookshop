package bookshop.security;

import bookshop.controller.response.GlobalResponse;
import bookshop.dto.UserAuthDto;
import bookshop.exception.ForbiddenException;
import bookshop.service.AuthService;
import bookshop.util.Constants;
import bookshop.util.StatusEnum;
import org.jboss.resteasy.annotations.interception.SecurityPrecedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import java.util.List;

/**
 * 鉴权用请求拦截器
 *
 * @author kaaass
 */
@Provider
@ServerInterceptor
@SecurityPrecedence
public class SecurityPreProcessInterceptor implements PreProcessInterceptor {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SecurityPreProcessInterceptor.class);
    @Inject
    private AuthService service;

    @Inject
    private SecurityIdentity identity;

    @Override
    public ServerResponse preProcess(HttpRequest request, ResourceMethod method)
            throws Failure, WebApplicationException {
        // 获得注解
        final Secured annotate = method.getMethod().getAnnotation(Secured.class);
        if (annotate == null) {
            return null;
        }
        // 如果不需要登录
        final SecurityRole requiredRole = annotate.value();
        if (requiredRole.lowThan(SecurityRole.LOGGED)) {
            return null;
        }
        // 需要登录时，获取登录令牌
        final List<String> headers = request.getHttpHeaders().getRequestHeader(Constants.HEADER_AUTH);
        if (headers == null || headers.isEmpty()) {
            return ServerResponse.copyIfNotServerResponse(
                    GlobalResponse.fail(StatusEnum.FORBIDDEN, "接口需要鉴权").toResponse());
        }
        final String authToken = headers.get(0);
        // 校验
        UserAuthDto authDto;
        try {
            authDto = service.validate(authToken);
        } catch (ForbiddenException e) {
            log.info("鉴权失败！AT = {}", authToken);
            return ServerResponse.copyIfNotServerResponse(GlobalResponse.fail(e).toResponse());
        }
        // 设定请求中有效的鉴权信息
        log.info("鉴权成功！ID = {}", authDto.getId());
        identity.setUserAuthDto(authDto);
        return null;
    }
}
