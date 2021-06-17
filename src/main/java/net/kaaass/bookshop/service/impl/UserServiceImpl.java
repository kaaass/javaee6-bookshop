package net.kaaass.bookshop.service.impl;

import java8.util.function.Function;
import java8.util.function.Predicate;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import net.kaaass.bookshop.dao.entity.UserAddressEntity;
import net.kaaass.bookshop.dao.entity.UserAuthEntity;
import net.kaaass.bookshop.dao.entity.UserInfoEntity;
import net.kaaass.bookshop.dao.repository.UserAddressRepository;
import net.kaaass.bookshop.dao.repository.UserAuthRepository;
import net.kaaass.bookshop.dao.repository.UserInfoRepository;
import net.kaaass.bookshop.dto.UserAddressDto;
import net.kaaass.bookshop.dto.UserInfoDto;
import net.kaaass.bookshop.exception.BaseException;
import net.kaaass.bookshop.exception.NotFoundException;
import net.kaaass.bookshop.mapper.UserMapper;
import net.kaaass.bookshop.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
@Slf4j
public class UserServiceImpl implements UserService, Serializable {

    @Inject
    private UserAddressRepository userAddressRepository;

    @Inject
    private UserInfoRepository userInfoRepository;

    @Inject
    private UserAuthRepository userAuthRepository;

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
        return UserMapper.INSTANCE.userAddressEntityToDto(getAddressEntityById(id));
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
                        return UserMapper.INSTANCE.userInfoEntityToDto(userInfoEntity);
                    }
                })
                .collect(Collectors.<UserInfoDto>toList());
    }
}
