package bookshop.mapper.impl;

import bookshop.dao.entity.*;
import bookshop.dto.*;
import bookshop.mapper.ProductMapper;

import javax.annotation.Generated;
import javax.enterprise.context.ApplicationScoped;
import java.sql.Timestamp;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2021-06-18T00:00:27+0800",
        comments = "version: 1.2.0.Final, compiler: javac, environment: Java 15.0.2 (N/A)"
)
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
        productDto.setThumbnail(mediaEntityToMediaDto(productEntity.getThumbnail()));
        productDto.setPrice(productEntity.getPrice());
        productDto.setMailPrice(productEntity.getMailPrice());
        productDto.setBuyLimit(productEntity.getBuyLimit());
        productDto.setCategory(categoryEntityToDto(productEntity.getCategory()));
        productDto.setIsbn(productEntity.getIsbn());
        productDto.setAuthor(productEntity.getAuthor());
        productDto.setPublishDate(productEntity.getPublishDate());
        productDto.setIndexOrder(productEntity.getIndexOrder());
        productDto.setStorage(productStorageEntityToDto(productEntity.getStorage()));
        productDto.setStartSellTime(productEntity.getStartSellTime());

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
        productEntity1.setThumbnail(mediaDtoToMediaEntity(productEntity.getThumbnail()));
        productEntity1.setPrice(productEntity.getPrice());
        productEntity1.setMailPrice(productEntity.getMailPrice());
        productEntity1.setBuyLimit(productEntity.getBuyLimit());
        productEntity1.setCategory(categoryDtoToCategoryEntity(productEntity.getCategory()));
        productEntity1.setIndexOrder(productEntity.getIndexOrder());
        productEntity1.setIsbn(productEntity.getIsbn());
        productEntity1.setAuthor(productEntity.getAuthor());
        if (productEntity.getPublishDate() != null) {
            productEntity1.setPublishDate(new Timestamp(productEntity.getPublishDate().getTime()));
        }
        productEntity1.setStorage(productStorageDtoToProductStorageEntity(productEntity.getStorage()));
        if (productEntity.getStartSellTime() != null) {
            productEntity1.setStartSellTime(new Timestamp(productEntity.getStartSellTime().getTime()));
        }

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

    protected MediaEntity mediaDtoToMediaEntity(MediaDto mediaDto) {
        if (mediaDto == null) {
            return null;
        }

        MediaEntity mediaEntity = new MediaEntity();

        mediaEntity.setId(mediaDto.getId());
        mediaEntity.setType(mediaDto.getType());
        mediaEntity.setUrl(mediaDto.getUrl());
        mediaEntity.setUploaderUid(mediaDto.getUploaderUid());
        if (mediaDto.getUploadTime() != null) {
            mediaEntity.setUploadTime(new Timestamp(mediaDto.getUploadTime().getTime()));
        }

        return mediaEntity;
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
