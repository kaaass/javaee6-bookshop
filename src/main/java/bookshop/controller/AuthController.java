package bookshop.controller;

import java8.util.function.Function;
import bookshop.controller.request.RegisterRequest;
import bookshop.controller.response.LoginResponse;
import bookshop.dto.UserAuthDto;
import bookshop.exception.BadRequestException;
import bookshop.exception.BaseException;
import bookshop.exception.ForbiddenException;
import bookshop.mapper.UserMapper;
import bookshop.service.AuthService;
import bookshop.vo.UserAuthVo;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthController extends BaseController {

    @EJB
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
