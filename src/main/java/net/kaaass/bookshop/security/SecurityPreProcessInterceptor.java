package net.kaaass.bookshop.security;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.kaaass.bookshop.controller.response.GlobalResponse;
import net.kaaass.bookshop.dto.UserAuthDto;
import net.kaaass.bookshop.exception.ForbiddenException;
import net.kaaass.bookshop.service.AuthService;
import net.kaaass.bookshop.util.Constants;
import org.jboss.resteasy.annotations.interception.SecurityPrecedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

/**
 * 鉴权用请求拦截器
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
        // 不知道为什么 @Inject 不起作用，手工查询
        try {
            val ctx = new InitialContext();
            service = (AuthService) ctx.lookup("java:module/AuthServiceImpl");
        } catch (NamingException e) {
            log.error("AuthService 查询失败", e);
        }
        // 获得注解
        val annotate = method.getMethod().getAnnotation(Secured.class);
        if (annotate == null) {
            return null;
        }
        // 如果不需要登录
        val requiredRole = annotate.role();
        if (requiredRole.lowThan(SecurityRole.LOGGED)) {
            return null;
        }
        // 需要登录时，获取登录令牌
        val headers = request.getHttpHeaders().getRequestHeader(Constants.HEADER_AUTH);
        val authToken = headers.get(0);
        // 校验
        UserAuthDto authDto;
        try {
            authDto = service.validate(authToken);
        } catch (ForbiddenException e) {
            log.debug("鉴权失败！AT = {}", authToken);
            return ServerResponse.copyIfNotServerResponse(GlobalResponse.fail(e).toResponse());
        }
        // 设定请求中有效的鉴权信息
        log.debug("鉴权成功！ID = {}", authDto.getId());
        identity.setUserAuthDto(authDto);
        return null;
    }
}
