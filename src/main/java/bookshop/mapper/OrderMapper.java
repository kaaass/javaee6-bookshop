package bookshop.mapper;

import bookshop.dao.entity.OrderEntity;
import bookshop.dao.entity.OrderItemEntity;
import bookshop.dto.OrderDto;
import bookshop.dto.OrderItemDto;

public interface OrderMapper {

    OrderDto orderEntityToDto(OrderEntity orderEntity);

    OrderItemDto orderItemEntityToDto(OrderItemEntity orderItemEntity);

    OrderItemEntity orderItemDtoToEntity(OrderItemDto orderItemDto);
}
