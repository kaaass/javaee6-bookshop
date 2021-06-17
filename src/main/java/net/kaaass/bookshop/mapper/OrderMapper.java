package net.kaaass.bookshop.mapper;

import net.kaaass.bookshop.dao.entity.OrderEntity;
import net.kaaass.bookshop.dao.entity.OrderItemEntity;
import net.kaaass.bookshop.dto.OrderDto;
import net.kaaass.bookshop.dto.OrderItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi", uses = CommonTransform.class)
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto orderEntityToDto(OrderEntity orderEntity);

    OrderItemDto orderItemEntityToDto(OrderItemEntity orderItemEntity);

    OrderItemEntity orderItemDtoToEntity(OrderItemDto orderItemDto);
}
