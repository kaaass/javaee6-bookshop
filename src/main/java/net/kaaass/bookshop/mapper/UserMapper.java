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
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 用户对象映射
 * @author kaaass
 */
@Mapper(componentModel = "cdi", uses = CommonTransform.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserAuthDto userAuthEntityToDto(UserAuthEntity authEntity);

    UserAuthVo userAuthDtoToVo(UserAuthDto authDto);

    UserAddressEntity userAddressRequestToEntity(UserAddressRequest request);

    UserAddressDto userAddressEntityToDto(UserAddressEntity addressEntity);

    UserInfoDto userInfoEntityToDto(UserInfoEntity userInfoEntity);

    @Mapping(target = "avatar", source = "user", qualifiedByName = "getAvatarFromAuth")
    CommentVo commentEntityToVo(CommentEntity commentEntity);
}
