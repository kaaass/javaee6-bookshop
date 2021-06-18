package bookshop.service;

import bookshop.dao.entity.UserAddressEntity;
import bookshop.dao.entity.UserAuthEntity;
import bookshop.dto.UserAddressDto;
import bookshop.dto.UserInfoDto;
import bookshop.exception.NotFoundException;

import javax.ejb.Local;
import java.util.List;

/**
 * 用户服务
 *
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
