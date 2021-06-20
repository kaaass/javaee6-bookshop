package bookshop.service;

import bookshop.controller.request.RegisterRequest;
import bookshop.controller.response.LoginResponse;
import bookshop.dto.UserAuthDto;
import bookshop.exception.ForbiddenException;
import bookshop.exception.NotFoundException;
import java8.util.Optional;

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