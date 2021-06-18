package net.kaaass.bookshop.promote.collector;

import java8.util.function.Function;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import lombok.val;
import net.kaaass.bookshop.dto.OrderItemDto;
import net.kaaass.bookshop.mapper.EntityCreator;
import net.kaaass.bookshop.promote.*;

/**
 * 显示打折用收集器
 */
public class ViewCollector implements IPromoteCollector<OrderPromoteContext> {

    public static ViewCollector INSTANCE = new ViewCollector();

    @Override
    public IPromoteStrategy.ResultType getInfoType() {
        return IPromoteStrategy.ResultType.NOT_COND;
    }

    @Override
    public OrderPromoteResult collect(OrderPromoteContext context) {
        val result = new OrderPromoteResult();
        result.setPrice(context.getPrice()); // 仅仅是商品价格
        result.setMailPrice(context.getMailPrice());
        result.setPromotes(context.getPromotes());
        // 商品映射
        result.setProducts(StreamSupport.stream(context.getProducts())
                .map(new Function<PromoteItem, OrderItemDto>() {
                    @Override
                    public OrderItemDto apply(PromoteItem promoteItem) {
                        return EntityCreator.INSTANCE.createOrderItemDto(promoteItem);
                    }
                })
                .collect(Collectors.<OrderItemDto>toList()));
        return result;
    }
}
