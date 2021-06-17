package net.kaaass.bookshop.service;

import java8.util.Optional;
import net.kaaass.bookshop.controller.request.RegisterRequest;
import net.kaaass.bookshop.controller.response.LoginResponse;
import net.kaaass.bookshop.dto.UserAuthDto;
import net.kaaass.bookshop.exception.ForbiddenException;
import net.kaaass.bookshop.exception.NotFoundException;

import javax.ejb.Local;

/**
 * 鉴权服务
 *
 * @author kaaass
 */
@Local
public interface AuthService {
    /**
     * 用户注册
     */
    Optional<UserAuthDto> register(RegisterRequest request);

    /**
     * 用户登录
     */
    Optional<LoginResponse> login(String phone, String password);

    /**
     * 校验鉴权令牌
     */
    UserAuthDto validate(String authToken) throws ForbiddenException;

    /**
     * 删除用户
     */
    void remove(String id) throws NotFoundException;
}