package net.kaaass.bookshop.service.impl;

import java8.util.Optional;
import java8.util.function.Function;
import net.kaaass.bookshop.controller.request.RegisterRequest;
import net.kaaass.bookshop.controller.response.LoginResponse;
import net.kaaass.bookshop.dao.entity.UserAuthEntity;
import net.kaaass.bookshop.dao.entity.UserInfoEntity;
import net.kaaass.bookshop.dao.repository.UserAuthRepository;
import net.kaaass.bookshop.dao.repository.UserInfoRepository;
import net.kaaass.bookshop.dto.UserAuthDto;
import net.kaaass.bookshop.exception.BaseException;
import net.kaaass.bookshop.exception.ForbiddenException;
import net.kaaass.bookshop.exception.NotFoundException;
import net.kaaass.bookshop.mapper.UserMapper;
import net.kaaass.bookshop.security.SecurityRole;
import net.kaaass.bookshop.service.AuthService;
import net.kaaass.bookshop.vo.AuthTokenVo;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * 鉴权服务
 * @author kaaass
 */
@Stateless
public class AuthServiceImpl implements AuthService, Serializable {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AuthServiceImpl.class);
    @Inject
    private UserAuthRepository repository;

    @Inject
    private UserInfoRepository infoRepository;

    @Inject
    private UserMapper userMapper;

    @Override
    public Optional<UserAuthDto> register(RegisterRequest request) {
        // 检查重复
        final String phone = request.getPhone();
        if (repository.findByPhone(phone).isPresent()) {
            log.info("用户 Phone = {} 已经存在", phone);
            return Optional.empty();
        }
        // 拼接登录信息
        UserAuthEntity authEntity = new UserAuthEntity();
        authEntity.setPhone(request.getPhone());
        authEntity.setPassword(request.getPassword());
        authEntity.setRole(SecurityRole.USER.toString());
        // 创建用户实体
        try {
            authEntity = repository.save(authEntity);
        } catch (Exception e) {
            return Optional.empty();
        }
        // 用户信息
        UserInfoEntity infoEntity = new UserInfoEntity();
        infoEntity.setAuth(authEntity);
        infoRepository.save(infoEntity);
        // 拼接结果
        return Optional.of(userMapper.userAuthEntityToDto(authEntity));
    }

    @Override
    public Optional<LoginResponse> login(String phone, String password) {
        try {
            final UserAuthEntity entity = repository.findByPhone(phone).orElseThrow();

            // 登录判断
            final String truePasswd = entity.getPassword();
            if (!truePasswd.equals(password)) {
                log.info("密码错误 phone = {}, password = {}", phone, password);
                return Optional.empty();
            }

            // 生成 Token
            String token = UUID.randomUUID().toString();
            Date signTime = new Date();

            // 保存 Token、更新时间
            entity.setAuthToken(token);
            entity.setLastLoginTime(new Timestamp(signTime.getTime()));
            repository.save(entity);

            // 拼接凭据
            return Optional.of(new LoginResponse(
                    new AuthTokenVo(token, signTime),
                    phone,
                    SecurityRole.valueOf(entity.getRole()).canPermitRole(SecurityRole.ADMIN)
            ));
        } catch (NoSuchElementException e) {
            log.info("账户 {} 不存在", phone);
            return Optional.empty();
        }
    }

    @Override
    public UserAuthDto validate(String authToken) throws ForbiddenException {
        return repository.findByAuthToken(authToken)
                .map(new Function<UserAuthEntity, UserAuthDto>() {
                    @Override
                    public UserAuthDto apply(UserAuthEntity entity) {
                        return userMapper.userAuthEntityToDto(entity);
                    }
                })
                .orElseThrow(BaseException.supplier(ForbiddenException.class, "鉴权令牌不存在"));
    }

    @Override
    public void remove(String id) throws NotFoundException {
        final UserAuthEntity entity = repository.findById(id)
                .orElseThrow(BaseException.supplier(NotFoundException.class, "未找到该用户！"));
        infoRepository.deleteAllByAuth(entity);
        repository.delete(entity);
    }
}
