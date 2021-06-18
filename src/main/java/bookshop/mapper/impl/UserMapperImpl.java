package bookshop.mapper.impl;

import bookshop.controller.request.UserAddressRequest;
import bookshop.dao.entity.*;
import bookshop.dto.*;
import bookshop.mapper.CommonTransform;
import bookshop.mapper.UserMapper;
import bookshop.security.SecurityRole;
import bookshop.vo.CommentVo;
import bookshop.vo.UserAuthVo;

import javax.annotation.Generated;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2021-06-18T00:00:27+0800",
        comments = "version: 1.2.0.Final, compiler: javac, environment: Java 15.0.2 (N/A)"
)
@ApplicationScoped
public class UserMapperImpl implements UserMapper {

    @Inject
    private CommonTransform commonTransform;

    @Override
    public UserAuthDto userAuthEntityToDto(UserAuthEntity authEntity) {
        if (authEntity == null) {
            return null;
        }

        UserAuthDto userAuthDto = new UserAuthDto();

        userAuthDto.setId(authEntity.getId());
        userAuthDto.setPhone(authEntity.getPhone());
        userAuthDto.setPassword(authEntity.getPassword());
        if (authEntity.getRole() != null) {
            userAuthDto.setRole(Enum.valueOf(SecurityRole.class, authEntity.getRole()));
        }

        return userAuthDto;
    }

    @Override
    public UserAuthVo userAuthDtoToVo(UserAuthDto authDto) {
        if (authDto == null) {
            return null;
        }

        UserAuthVo userAuthVo = new UserAuthVo();

        userAuthVo.setId(authDto.getId());
        userAuthVo.setPhone(authDto.getPhone());
        if (authDto.getRole() != null) {
            userAuthVo.setRole(authDto.getRole().name());
        }

        return userAuthVo;
    }

    @Override
    public UserAddressEntity userAddressRequestToEntity(UserAddressRequest request) {
        if (request == null) {
            return null;
        }

        UserAddressEntity userAddressEntity = new UserAddressEntity();

        userAddressEntity.setArea(request.getArea());
        userAddressEntity.setDetailAddress(request.getDetailAddress());
        userAddressEntity.setMailCode(request.getMailCode());
        userAddressEntity.setPhone(request.getPhone());
        userAddressEntity.setName(request.getName());

        return userAddressEntity;
    }

    @Override
    public UserAddressDto userAddressEntityToDto(UserAddressEntity addressEntity) {
        if (addressEntity == null) {
            return null;
        }

        UserAddressDto userAddressDto = new UserAddressDto();

        userAddressDto.setId(addressEntity.getId());
        userAddressDto.setArea(addressEntity.getArea());
        userAddressDto.setDetailAddress(addressEntity.getDetailAddress());
        userAddressDto.setMailCode(addressEntity.getMailCode());
        userAddressDto.setPhone(addressEntity.getPhone());
        userAddressDto.setName(addressEntity.getName());
        userAddressDto.setDefaultAddress(addressEntity.isDefaultAddress());

        return userAddressDto;
    }

    @Override
    public UserAddressEntity userAddressDtoToEntity(UserAddressDto addressEntity) {
        if (addressEntity == null) {
            return null;
        }

        UserAddressEntity userAddressEntity = new UserAddressEntity();

        userAddressEntity.setId(addressEntity.getId());
        userAddressEntity.setArea(addressEntity.getArea());
        userAddressEntity.setDetailAddress(addressEntity.getDetailAddress());
        userAddressEntity.setMailCode(addressEntity.getMailCode());
        userAddressEntity.setPhone(addressEntity.getPhone());
        userAddressEntity.setName(addressEntity.getName());
        userAddressEntity.setDefaultAddress(addressEntity.isDefaultAddress());

        return userAddressEntity;
    }

    @Override
    public UserInfoDto userInfoEntityToDto(UserInfoEntity userInfoEntity) {
        if (userInfoEntity == null) {
            return null;
        }

        UserInfoDto userInfoDto = new UserInfoDto();

        userInfoDto.setAuth(userAuthEntityToDto(userInfoEntity.getAuth()));
        userInfoDto.setWechat(userInfoEntity.getWechat());
        userInfoDto.setAvatar(mediaEntityToMediaDto(userInfoEntity.getAvatar()));

        return userInfoDto;
    }

    @Override
    public CommentDto commentEntityToDto(CommentEntity commentEntity) {
        if (commentEntity == null) {
            return null;
        }

        CommentDto commentDto = new CommentDto();

        commentDto.setId(commentEntity.getId());
        commentDto.setOrderId(commentEntity.getOrderId());
        commentDto.setProductId(commentEntity.getProductId());
        commentDto.setRate(commentEntity.getRate());
        commentDto.setContent(commentEntity.getContent());
        commentDto.setCommentTime(commentEntity.getCommentTime());

        return commentDto;
    }

    @Override
    public CommentVo commentEntityToVo(CommentEntity commentEntity) {
        if (commentEntity == null) {
            return null;
        }

        CommentVo commentVo = new CommentVo();

        commentVo.setAvatar(commonTransform.getAvatarFromAuth(commentEntity.getUser()));
        commentVo.setId(commentEntity.getId());
        commentVo.setRate(commentEntity.getRate());
        commentVo.setContent(commentEntity.getContent());
        commentVo.setCommentTime(commentEntity.getCommentTime());

        return commentVo;
    }

    protected MediaDto mediaEntityToMediaDto(MediaEntity mediaEntity) {
        if (mediaEntity == null) {
            return null;
        }

        MediaDto mediaDto = new MediaDto();

        mediaDto.setId(mediaEntity.getId());
        mediaDto.setType(mediaEntity.getType());
        mediaDto.setUrl(mediaEntity.getUrl());
        mediaDto.setUploaderUid(mediaEntity.getUploaderUid());
        mediaDto.setUploadTime(mediaEntity.getUploadTime());

        return mediaDto;
    }
}