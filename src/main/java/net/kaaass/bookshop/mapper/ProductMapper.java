package net.kaaass.bookshop.mapper;

import net.kaaass.bookshop.dao.entity.CategoryEntity;
import net.kaaass.bookshop.dao.entity.ProductEntity;
import net.kaaass.bookshop.dao.entity.ProductMetadataEntity;
import net.kaaass.bookshop.dao.entity.ProductStorageEntity;
import net.kaaass.bookshop.dto.CategoryDto;
import net.kaaass.bookshop.dto.ProductDto;
import net.kaaass.bookshop.dto.ProductMetadataDto;
import net.kaaass.bookshop.dto.ProductStorageDto;

/**
 * 商品对象映射
 * @author kaaass
 */
public interface ProductMapper {

    ProductDto productEntityToDto(ProductEntity productEntity);

    ProductEntity productDtoToEntity(ProductDto productEntity);

    CategoryDto categoryEntityToDto(CategoryEntity categoryEntity);

    ProductMetadataDto productMetadataEntityToDto(ProductMetadataEntity productMetadataEntity);

    ProductStorageDto productStorageEntityToDto(ProductStorageEntity productStorageEntity);
}
