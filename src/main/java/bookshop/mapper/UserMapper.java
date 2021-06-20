package bookshop.mapper;

import bookshop.controller.request.UserAddressRequest;
import bookshop.dao.entity.UserAddressEntity;
import bookshop.dao.entity.UserAuthEntity;
import bookshop.dto.UserAddressDto;
import bookshop.dto.UserAuthDto;
import bookshop.vo.CommentVo;
import bookshop.vo.UserAuthVo;

/**
 * 用户对象映射
 *
 * @author kaaass
 */
public interface UserMapper {

    UserAuthDto userAuthEntityToDto(UserAuthEntity authEntity);

    UserAuthVo userAuthDtoToVo(UserAuthDto authDto);

    UserAddressEntity userAddressRequestToEntity(UserAddressRequest request);

    UserAddressDto userAddressEntityToDto(UserAddressEntity addressEntity);

    UserAddressEntity userAddressDtoToEntity(UserAddressDto addressEntity);
}
