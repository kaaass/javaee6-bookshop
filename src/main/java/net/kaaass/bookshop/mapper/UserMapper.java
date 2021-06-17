package net.kaaass.bookshop.mapper;

import net.kaaass.bookshop.controller.request.UserAddressRequest;
import net.kaaass.bookshop.dao.entity.CommentEntity;
import net.kaaass.bookshop.dao.entity.UserAddressEntity;
import net.kaaass.bookshop.dao.entity.UserAuthEntity;
import net.kaaass.bookshop.dao.entity.UserInfoEntity;
import net.kaaass.bookshop.dto.CommentDto;
import net.kaaass.bookshop.dto.UserAddressDto;
import net.kaaass.bookshop.dto.UserAuthDto;
import net.kaaass.bookshop.dto.UserInfoDto;
import net.kaaass.bookshop.vo.CommentVo;
import net.kaaass.bookshop.vo.UserAuthVo;

/**
 * 用户对象映射
 * @author kaaass
 */
public interface UserMapper {

    UserAuthDto userAuthEntityToDto(UserAuthEntity authEntity);

    UserAuthVo userAuthDtoToVo(UserAuthDto authDto);

    UserAddressEntity userAddressRequestToEntity(UserAddressRequest request);

    UserAddressDto userAddressEntityToDto(UserAddressEntity addressEntity);

    UserAddressEntity userAddressDtoToEntity(UserAddressDto addressEntity);

    UserInfoDto userInfoEntityToDto(UserInfoEntity userInfoEntity);

    CommentDto commentEntityToDto(CommentEntity commentEntity);

    CommentVo commentEntityToVo(CommentEntity commentEntity);
}
