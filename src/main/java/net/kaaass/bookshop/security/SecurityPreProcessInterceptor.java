package net.kaaass.bookshop.security;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.kaaass.bookshop.controller.response.GlobalResponse;
import net.kaaass.bookshop.dto.UserAuthDto;
import net.kaaass.bookshop.exception.ForbiddenException;
import net.kaaass.bookshop.service.AuthService;
import net.kaaass.bookshop.util.Constants;
import net.kaaass.bookshop.util.StatusEnum;
import org.jboss.resteasy.annotations.interception.SecurityPrecedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

/**
 * 鉴权用请求拦截器
 *
 * @author kaaass
 */
@Slf4j
@Provider
@ServerInterceptor
@SecurityPrecedence
public class SecurityPreProcessInterceptor implements PreProcessInterceptor {

    @Inject
    private AuthService service;

    @Inject
    private SecurityIdentity identity;

    @Override
    public ServerResponse preProcess(HttpRequest request, ResourceMethod method)
            throws Failure, WebApplicationException {
        // 获得注解
        val annotate = method.getMethod().getAnnotation(Secured.class);
        if (annotate == null) {
            return null;
        }
        // 如果不需要登录
        val requiredRole = annotate.value();
        if (requiredRole.lowThan(SecurityRole.LOGGED)) {
            return null;
        }
        // 需要登录时，获取登录令牌
        val headers = request.getHttpHeaders().getRequestHeader(Constants.HEADER_AUTH);
        if (headers == null || headers.isEmpty()) {
            return ServerResponse.copyIfNotServerResponse(
                    GlobalResponse.fail(StatusEnum.FORBIDDEN, "接口需要鉴权").toResponse());
        }
        val authToken = headers.get(0);
        // 校验
        UserAuthDto authDto;
        try {
            authDto = service.validate(authToken);
            if (!authDto.getRole().canPermitRole(requiredRole)) {
                throw new ForbiddenException("权限不足！");
            }
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
