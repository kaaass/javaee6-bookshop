package net.kaaass.bookshop.mapper;

import java8.util.function.Function;
import net.kaaass.bookshop.dao.entity.UserAuthEntity;
import net.kaaass.bookshop.dto.UserAuthDto;
import net.kaaass.bookshop.vo.UserAuthVo;

public class Mapper {

    public final static Function<UserAuthEntity, UserAuthDto> userAuthEntityToDto = new Function<UserAuthEntity, UserAuthDto>() {
        @Override
        public UserAuthDto apply(UserAuthEntity entity) {
            return UserMapper.INSTANCE.userAuthEntityToDto(entity);
        }
    };

    public final static Function<UserAuthDto, UserAuthVo> userAuthDtoToVo = new Function<UserAuthDto, UserAuthVo>() {
        @Override
        public UserAuthVo apply(UserAuthDto dto) {
            return UserMapper.INSTANCE.userAuthDtoToVo(dto);
        }
    };
}
