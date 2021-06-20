package bookshop.service.impl;

import bookshop.dao.entity.UserAddressEntity;
import bookshop.dao.entity.UserAuthEntity;
import bookshop.dao.repository.UserAddressRepository;
import bookshop.dao.repository.UserAuthRepository;
import bookshop.dto.UserAddressDto;
import bookshop.exception.BaseException;
import bookshop.exception.NotFoundException;
import bookshop.mapper.UserMapper;
import bookshop.service.UserService;
import bookshop.vo.UserAuthVo;
import java8.util.function.Function;
import java8.util.function.Predicate;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import org.slf4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.List;

@Stateless
public class UserServiceImpl implements UserService, Serializable {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);

    @EJB
    private UserAddressRepository userAddressRepository;

    @EJB
    private UserAuthRepository userAuthRepository;

    @EJB
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
    public List<UserAuthVo> getAllUser() {
        return StreamSupport.stream(userAuthRepository.findAll())
                .map(new Function<UserAuthEntity, UserAuthVo>() {
                    @Override
                    public UserAuthVo apply(UserAuthEntity userInfoEntity) {
                        return userMapper.userAuthDtoToVo(userMapper.userAuthEntityToDto(userInfoEntity));
                    }
                })
                .collect(Collectors.<UserAuthVo>toList());
    }
}
