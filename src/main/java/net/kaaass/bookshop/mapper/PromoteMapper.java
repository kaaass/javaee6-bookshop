package net.kaaass.bookshop.mapper;

import net.kaaass.bookshop.dao.entity.CartEntity;
import net.kaaass.bookshop.dto.OrderItemDto;
import net.kaaass.bookshop.promote.PromoteItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PromoteMapper {
    PromoteMapper INSTANCE = Mappers.getMapper(PromoteMapper.class);

    PromoteItem cartEntityToPromoteItem(CartEntity cartEntity);

    OrderItemDto promoteItemToOrderItemDto(PromoteItem promoteItem);
}
