package bookshop.mapper.impl;

import bookshop.dao.entity.CategoryEntity;
import bookshop.dao.entity.ProductEntity;
import bookshop.dao.entity.ProductStorageEntity;
import bookshop.dto.CategoryDto;
import bookshop.dto.ProductDto;
import bookshop.dto.ProductStorageDto;
import bookshop.mapper.ProductMapper;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import java.sql.Timestamp;

@Singleton
@ApplicationScoped
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto productEntityToDto(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId(productEntity.getId());
        productDto.setName(productEntity.getName());
        productDto.setThumbnail(productEntity.getThumbnail());
        productDto.setPrice(productEntity.getPrice());
        productDto.setCategory(categoryEntityToDto(productEntity.getCategory()));
        productDto.setIsbn(productEntity.getIsbn());
        productDto.setAuthor(productEntity.getAuthor());
        productDto.setPublishDate(productEntity.getPublishDate());
        productDto.setStorage(productStorageEntityToDto(productEntity.getStorage()));

        return productDto;
    }

    @Override
    public ProductEntity productDtoToEntity(ProductDto productEntity) {
        if (productEntity == null) {
            return null;
        }

        ProductEntity productEntity1 = new ProductEntity();

        productEntity1.setId(productEntity.getId());
        productEntity1.setName(productEntity.getName());
        productEntity1.setThumbnail(productEntity.getThumbnail());
        productEntity1.setPrice(productEntity.getPrice());
        productEntity1.setCategory(categoryDtoToCategoryEntity(productEntity.getCategory()));
        productEntity1.setIsbn(productEntity.getIsbn());
        productEntity1.setAuthor(productEntity.getAuthor());
        if (productEntity.getPublishDate() != null) {
            productEntity1.setPublishDate(new Timestamp(productEntity.getPublishDate().getTime()));
        }
        productEntity1.setStorage(productStorageDtoToProductStorageEntity(productEntity.getStorage()));

        return productEntity1;
    }

    @Override
    public CategoryDto categoryEntityToDto(CategoryEntity categoryEntity) {
        if (categoryEntity == null) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(categoryEntity.getId());
        categoryDto.setName(categoryEntity.getName());
        categoryDto.setParent(categoryEntityToDto(categoryEntity.getParent()));
        categoryDto.setCreateTime(categoryEntity.getCreateTime());

        return categoryDto;
    }

    @Override
    public ProductStorageDto productStorageEntityToDto(ProductStorageEntity productStorageEntity) {
        if (productStorageEntity == null) {
            return null;
        }

        ProductStorageDto productStorageDto = new ProductStorageDto();

        productStorageDto.setId(productStorageEntity.getId());
        productStorageDto.setRest(productStorageEntity.getRest());

        return productStorageDto;
    }

    protected CategoryEntity categoryDtoToCategoryEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setId(categoryDto.getId());
        categoryEntity.setName(categoryDto.getName());
        categoryEntity.setParent(categoryDtoToCategoryEntity(categoryDto.getParent()));
        if (categoryDto.getCreateTime() != null) {
            categoryEntity.setCreateTime(new Timestamp(categoryDto.getCreateTime().getTime()));
        }

        return categoryEntity;
    }

    protected ProductStorageEntity productStorageDtoToProductStorageEntity(ProductStorageDto productStorageDto) {
        if (productStorageDto == null) {
            return null;
        }

        ProductStorageEntity productStorageEntity = new ProductStorageEntity();

        productStorageEntity.setId(productStorageDto.getId());
        productStorageEntity.setRest(productStorageDto.getRest());

        return productStorageEntity;
    }
}
