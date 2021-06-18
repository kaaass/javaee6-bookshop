package bookshop.service.impl;

import java8.util.function.Function;
import java8.util.function.Predicate;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import bookshop.dao.entity.UserAddressEntity;
import bookshop.dao.entity.UserAuthEntity;
import bookshop.dao.entity.UserInfoEntity;
import bookshop.dao.repository.UserAddressRepository;
import bookshop.dao.repository.UserAuthRepository;
import bookshop.dao.repository.UserInfoRepository;
import bookshop.dto.UserAddressDto;
import bookshop.dto.UserInfoDto;
import bookshop.exception.BaseException;
import bookshop.exception.NotFoundException;
import bookshop.mapper.UserMapper;
import bookshop.service.UserService;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
public class UserServiceImpl implements UserService, Serializable {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);
    @Inject
    private UserAddressRepository userAddressRepository;

    @Inject
    private UserInfoRepository userInfoRepository;

    @Inject
    private UserAuthRepository userAuthRepository;

    @Inject
    private UserMapper userMapper;

    @Override
    public UserAuthEntity getAuthEntityById(String uid) throws NotFoundException {
        return userAuthRepository.findById(uid)
                .orElseThrow(BaseException.supplier(NotFoundException.class, "未找到用户！"));
    }

    @Override
    public UserAddressEntity getAddressEntityById(String id) throws NotFoundException {
        return userAddressRepository.findById(id)
                .orElseThrow(BaseException.supplier(NotFoundException.class, "未找到该地址！"));
    }

    @Override
    public UserAddressEntity getAddressEntityByIdAndCheck(String id, final String uid) throws NotFoundException {
        return userAddressRepository.findById(id)
                .filter(new Predicate<UserAddressEntity>() {
                    @Override
                    public boolean test(UserAddressEntity addressEntity) {
                        return addressEntity.getUser().getId().equals(uid);
                    }
                })
                .orElseThrow(BaseException.supplier(NotFoundException.class, "未找到该地址！"));
    }

    @Override
    public UserAddressDto getAddressById(String id) throws NotFoundException {
        return userMapper.userAddressEntityToDto(getAddressEntityById(id));
    }

    @Override
    public UserAddressEntity getDefaultAddressEntityById(String id) throws NotFoundException {
        return userAddressRepository.findFirstByUidAndDefaultAddressTrue(id)
                .orElseThrow(BaseException.supplier(NotFoundException.class, "未找到该地址！"));
    }

    @Override
    public List<UserInfoDto> getAllUser() {
        return StreamSupport.stream(userInfoRepository.findAll())
                .map(new Function<UserInfoEntity, UserInfoDto>() {
                    @Override
                    public UserInfoDto apply(UserInfoEntity userInfoEntity) {
                        return userMapper.userInfoEntityToDto(userInfoEntity);
                    }
                })
                .collect(Collectors.<UserInfoDto>toList());
    }
}