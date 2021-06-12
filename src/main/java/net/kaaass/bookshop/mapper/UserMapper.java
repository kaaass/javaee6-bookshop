package net.kaaass.bookshop.mapper;

import net.kaaass.bookshop.dao.entity.UserAuthEntity;
import net.kaaass.bookshop.dto.UserAuthDto;
import net.kaaass.bookshop.vo.UserAuthVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserAuthDto userAuthEntityToDto(UserAuthEntity authEntity);

    UserAuthVo userAuthDtoToVo(UserAuthDto authDto);
}
