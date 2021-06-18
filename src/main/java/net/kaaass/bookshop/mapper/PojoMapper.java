package net.kaaass.bookshop.mapper;

import net.kaaass.bookshop.dao.entity.*;
import net.kaaass.bookshop.dto.*;
import net.kaaass.bookshop.vo.CommentVo;
import net.kaaass.bookshop.vo.PromoteStrategyInfoVo;
import net.kaaass.bookshop.vo.UserAuthVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * POJO 映射
 *
 * @author kaaass
 */
@Mapper(componentModel = "cdi", uses = CommonTransform.class)
public interface PojoMapper {

    MediaDto entityToDto(MediaEntity metadataEntity);

    PluginDto entityToDto(PluginEntity pluginEntity);

    OrderDto entityToDto(OrderEntity orderEntity);

    PromoteStrategyDto entityToDto(PromoteStrategyEntity promoteStrategyEntity);

    PromoteStrategyInfoVo dtoToVo(PromoteStrategyDto promoteStrategyDto);

    ProductDto entityToDto(ProductEntity productEntity);

    CategoryDto entityToDto(CategoryEntity categoryEntity);

    ProductStorageDto entityToDto(ProductStorageEntity productStorageEntity);

    UserAuthDto entityToDto(UserAuthEntity authEntity);

    UserAuthVo dtoToVo(UserAuthDto authDto);

    UserAddressDto entityToDto(UserAddressEntity addressEntity);

    UserInfoDto entityToDto(UserInfoEntity userInfoEntity);

    @Mapping(target = "avatar", source = "user", qualifiedByName = "getAvatarFromAuth")
    CommentVo entityToVo(CommentEntity commentEntity);
}
