package bookshop.mapper.impl;

import bookshop.controller.request.UserAddressRequest;
import bookshop.dao.entity.*;
import bookshop.dto.*;
import bookshop.mapper.UserMapper;
import bookshop.security.SecurityRole;
import bookshop.vo.CommentVo;
import bookshop.vo.UserAuthVo;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Singleton
@ApplicationScoped
public class UserMapperImpl implements UserMapper {

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
}
