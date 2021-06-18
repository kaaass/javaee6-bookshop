package net.kaaass.bookshop.controller;

import java8.util.function.Function;
import net.kaaass.bookshop.controller.request.RegisterRequest;
import net.kaaass.bookshop.controller.response.LoginResponse;
import net.kaaass.bookshop.dto.UserAuthDto;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.exception.BaseException;
import net.kaaass.bookshop.exception.ForbiddenException;
import net.kaaass.bookshop.mapper.UserMapper;
import net.kaaass.bookshop.service.AuthService;
import net.kaaass.bookshop.vo.UserAuthVo;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthController extends BaseController {

    @Inject
    private AuthService service;

    @Inject
    private Validator validator;

    @Inject
    private UserMapper userMapper;

    @POST
    @Path("/login")
    public LoginResponse login(
            @QueryParam("phone") String phone,
            @QueryParam("password") String password) throws ForbiddenException {
        return service.login(phone, password)
                .orElseThrow(BaseException.supplier(ForbiddenException.class, "用户名或密码错误！"));
    }

    @POST
    @Path("/register")
    public UserAuthVo register(RegisterRequest request) throws BadRequestException {
        validateBean(validator, request);
        return service.register(request)
                .map(new Function<UserAuthDto, UserAuthVo>() {
                    @Override
                    public UserAuthVo apply(UserAuthDto dto) {
                        return userMapper.userAuthDtoToVo(dto);
                    }
                })
                .orElseThrow(BaseException.supplier(BadRequestException.class, "该手机号已被注册！"));
    }
}
