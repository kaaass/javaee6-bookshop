package bookshop.mapper;

import bookshop.dao.entity.CategoryEntity;
import bookshop.dao.entity.ProductEntity;
import bookshop.dao.entity.ProductStorageEntity;
import bookshop.dto.CategoryDto;
import bookshop.dto.ProductDto;
import bookshop.dto.ProductStorageDto;

/**
 * 商品对象映射
 *
 * @author kaaass
 */
public interface ProductMapper {

    ProductDto productEntityToDto(ProductEntity productEntity);

    ProductEntity productDtoToEntity(ProductDto productEntity);

    CategoryDto categoryEntityToDto(CategoryEntity categoryEntity);

    ProductStorageDto productStorageEntityToDto(ProductStorageEntity productStorageEntity);
}