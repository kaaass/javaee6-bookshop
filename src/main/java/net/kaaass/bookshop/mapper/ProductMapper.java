package net.kaaass.bookshop.mapper;

import net.kaaass.bookshop.dao.entity.CategoryEntity;
import net.kaaass.bookshop.dao.entity.ProductEntity;
import net.kaaass.bookshop.dao.entity.ProductStorageEntity;
import net.kaaass.bookshop.dto.CategoryDto;
import net.kaaass.bookshop.dto.ProductDto;
import net.kaaass.bookshop.dto.ProductStorageDto;
import org.mapstruct.Mapper;

/**
 * 商品对象映射
 *
 * @author kaaass
 */
@Mapper(componentModel = "cdi", uses = CommonTransform.class)
public interface ProductMapper {

    ProductDto productEntityToDto(ProductEntity productEntity);

    CategoryDto categoryEntityToDto(CategoryEntity categoryEntity);

    ProductStorageDto productStorageEntityToDto(ProductStorageEntity productStorageEntity);
}
