package net.kaaass.bookshop.mapper;

import net.kaaass.bookshop.dao.entity.*;
import net.kaaass.bookshop.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 商品对象映射
 * @author kaaass
 */
@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto productEntityToDto(ProductEntity productEntity);

    CategoryDto categoryEntityToDto(CategoryEntity categoryEntity);

    ProductMetadataDto productMetadataEntityToDto(ProductMetadataEntity productMetadataEntity);

    ProductStorageDto productStorageEntityToDto(ProductStorageEntity productStorageEntity);

    CartDto cartEntityToDto(CartEntity cartEntity);
}
