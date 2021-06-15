package net.kaaass.bookshop.service;

import net.kaaass.bookshop.dao.entity.UserAddressEntity;
import net.kaaass.bookshop.dao.entity.UserAuthEntity;
import net.kaaass.bookshop.dto.UserAddressDto;
import net.kaaass.bookshop.dto.UserInfoDto;
import net.kaaass.bookshop.exception.NotFoundException;

import javax.ejb.Local;
import java.util.List;

/**
 * 用户服务
 * @author kaaass
 */
@Local
public interface UserService {

    /**
     * @deprecated
     */
    UserAuthEntity getAuthEntityById(String uid) throws NotFoundException;

    /**
     * @deprecated
     */
    UserAddressEntity getAddressEntityById(String id) throws NotFoundException;

    /**
     * @deprecated
     */
    UserAddressEntity getAddressEntityByIdAndCheck(String id, String uid) throws NotFoundException;

    UserAddressDto getAddressById(String id) throws NotFoundException;

    UserAddressEntity getDefaultAddressEntityById(String id) throws NotFoundException;

    List<UserInfoDto> getAllUser();
}
