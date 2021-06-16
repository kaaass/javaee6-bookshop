package net.kaaass.bookshop.mapper;

import net.kaaass.bookshop.dto.CartDto;
import net.kaaass.bookshop.dto.OrderItemDto;
import net.kaaass.bookshop.promote.PromoteItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi", uses = CommonTransform.class)
public interface PromoteMapper {
    PromoteMapper INSTANCE = Mappers.getMapper(PromoteMapper.class);

    PromoteItem cartDtoToPromoteItem(CartDto cartDto);

    OrderItemDto promoteItemToOrderItemDto(PromoteItem promoteItem);
}
