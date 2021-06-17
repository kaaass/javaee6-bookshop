package net.kaaass.bookshop.mapper;

import net.kaaass.bookshop.dao.entity.OrderEntity;
import net.kaaass.bookshop.dao.entity.OrderItemEntity;
import net.kaaass.bookshop.dto.OrderDto;
import net.kaaass.bookshop.dto.OrderItemDto;

public interface OrderMapper {

    OrderDto orderEntityToDto(OrderEntity orderEntity);

    OrderItemDto orderItemEntityToDto(OrderItemEntity orderItemEntity);

    OrderItemEntity orderItemDtoToEntity(OrderItemDto orderItemDto);
}
