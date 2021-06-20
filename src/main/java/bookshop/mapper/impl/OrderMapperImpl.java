package bookshop.mapper.impl;

import bookshop.dao.entity.*;
import bookshop.dto.*;
import bookshop.mapper.OrderMapper;

import javax.annotation.Generated;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2021-06-18T00:00:27+0800",
        comments = "version: 1.2.0.Final, compiler: javac, environment: Java 15.0.2 (N/A)"
)
@ApplicationScoped
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDto orderEntityToDto(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setId(orderEntity.getId());
        orderDto.setUid(orderEntity.getUid());
        orderDto.setAddress(userAddressEntityToUserAddressDto(orderEntity.getAddress()));
        orderDto.setRequestId(orderEntity.getRequestId());
        orderDto.setType(orderEntity.getType());
        orderDto.setPrice(orderEntity.getPrice());
        orderDto.setMailPrice(orderEntity.getMailPrice());
        orderDto.setDeliverCode(orderEntity.getDeliverCode());
        orderDto.setReason(orderEntity.getReason());
        orderDto.setProducts(orderItemEntityListToOrderItemDtoList(orderEntity.getProducts()));
        orderDto.setCreateTime(orderEntity.getCreateTime());
        orderDto.setPayTime(orderEntity.getPayTime());
        orderDto.setDeliverTime(orderEntity.getDeliverTime());
        orderDto.setFinishTime(orderEntity.getFinishTime());
        orderDto.setRefundTime(orderEntity.getRefundTime());

        return orderDto;
    }

    @Override
    public OrderItemDto orderItemEntityToDto(OrderItemEntity orderItemEntity) {
        if (orderItemEntity == null) {
            return null;
        }

        OrderItemDto orderItemDto = new OrderItemDto();

        orderItemDto.setId(orderItemEntity.getId());
        orderItemDto.setProduct(productEntityToProductDto(orderItemEntity.getProduct()));
        orderItemDto.setPrice(orderItemEntity.getPrice());
        orderItemDto.setCount(orderItemEntity.getCount());

        return orderItemDto;
    }

    @Override
    public OrderItemEntity orderItemDtoToEntity(OrderItemDto orderItemDto) {
        if (orderItemDto == null) {
            return null;
        }

        OrderItemEntity orderItemEntity = new OrderItemEntity();

        orderItemEntity.setId(orderItemDto.getId());
        orderItemEntity.setProduct(productDtoToProductEntity(orderItemDto.getProduct()));
        orderItemEntity.setPrice(orderItemDto.getPrice());
        orderItemEntity.setCount(orderItemDto.getCount());

        return orderItemEntity;
    }

    protected UserAddressDto userAddressEntityToUserAddressDto(UserAddressEntity userAddressEntity) {
        if (userAddressEntity == null) {
            return null;
        }

        UserAddressDto userAddressDto = new UserAddressDto();

        userAddressDto.setId(userAddressEntity.getId());
        userAddressDto.setArea(userAddressEntity.getArea());
        userAddressDto.setDetailAddress(userAddressEntity.getDetailAddress());
        userAddressDto.setMailCode(userAddressEntity.getMailCode());
        userAddressDto.setPhone(userAddressEntity.getPhone());
        userAddressDto.setName(userAddressEntity.getName());
        userAddressDto.setDefaultAddress(userAddressEntity.isDefaultAddress());

        return userAddressDto;
    }

    protected List<OrderItemDto> orderItemEntityListToOrderItemDtoList(List<OrderItemEntity> list) {
        if (list == null) {
            return null;
        }

        List<OrderItemDto> list1 = new ArrayList<OrderItemDto>(list.size());
        for (OrderItemEntity orderItemEntity : list) {
            list1.add(orderItemEntityToDto(orderItemEntity));
        }

        return list1;
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

    protected CategoryDto categoryEntityToCategoryDto(CategoryEntity categoryEntity) {
        if (categoryEntity == null) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(categoryEntity.getId());
        categoryDto.setName(categoryEntity.getName());
        categoryDto.setParent(categoryEntityToCategoryDto(categoryEntity.getParent()));
        categoryDto.setCreateTime(categoryEntity.getCreateTime());

        return categoryDto;
    }

    protected ProductStorageDto productStorageEntityToProductStorageDto(ProductStorageEntity productStorageEntity) {
        if (productStorageEntity == null) {
            return null;
        }

        ProductStorageDto productStorageDto = new ProductStorageDto();

        productStorageDto.setId(productStorageEntity.getId());
        productStorageDto.setRest(productStorageEntity.getRest());

        return productStorageDto;
    }

    protected ProductDto productEntityToProductDto(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId(productEntity.getId());
        productDto.setName(productEntity.getName());
        productDto.setThumbnail(mediaEntityToMediaDto(productEntity.getThumbnail()));
        productDto.setPrice(productEntity.getPrice());
        productDto.setMailPrice(productEntity.getMailPrice());
        productDto.setCategory(categoryEntityToCategoryDto(productEntity.getCategory()));
        productDto.setIsbn(productEntity.getIsbn());
        productDto.setAuthor(productEntity.getAuthor());
        productDto.setPublishDate(productEntity.getPublishDate());
        productDto.setIndexOrder(productEntity.getIndexOrder());
        productDto.setStorage(productStorageEntityToProductStorageDto(productEntity.getStorage()));

        return productDto;
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

    protected ProductEntity productDtoToProductEntity(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setId(productDto.getId());
        productEntity.setName(productDto.getName());
        productEntity.setThumbnail(mediaDtoToMediaEntity(productDto.getThumbnail()));
        productEntity.setPrice(productDto.getPrice());
        productEntity.setMailPrice(productDto.getMailPrice());
        productEntity.setCategory(categoryDtoToCategoryEntity(productDto.getCategory()));
        productEntity.setIndexOrder(productDto.getIndexOrder());
        productEntity.setIsbn(productDto.getIsbn());
        productEntity.setAuthor(productDto.getAuthor());
        if (productDto.getPublishDate() != null) {
            productEntity.setPublishDate(new Timestamp(productDto.getPublishDate().getTime()));
        }
        productEntity.setStorage(productStorageDtoToProductStorageEntity(productDto.getStorage()));

        return productEntity;
    }
}
