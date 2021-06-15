package net.kaaass.bookshop.mapper;

import net.kaaass.bookshop.dao.entity.OrderEntity;
import net.kaaass.bookshop.dao.entity.OrderItemEntity;
import net.kaaass.bookshop.dao.entity.PromoteStrategyEntity;
import net.kaaass.bookshop.dto.OrderDto;
import net.kaaass.bookshop.dto.OrderItemDto;
import net.kaaass.bookshop.dto.PromoteStrategyDto;
import net.kaaass.bookshop.vo.PromoteStrategyInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto orderEntityToDto(OrderEntity orderEntity);

    OrderItemDto orderItemEntityToDto(OrderItemEntity orderItemEntity);

    OrderItemEntity orderItemDtoToEntity(OrderItemDto orderItemDto);

    PromoteStrategyDto promoteStrategyEntitiyToDto(PromoteStrategyEntity promoteStrategyEntity);

    PromoteStrategyEntity promoteStrategyDtoToEntitiy(PromoteStrategyDto promoteStrategyDto);

    PromoteStrategyInfoVo promoteStrategyDtoToInfoVo(PromoteStrategyDto promoteStrategyDto);
}
