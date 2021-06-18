package bookshop.dto;

import java8.util.Optional;
import java8.util.function.Predicate;
import java8.util.stream.StreamSupport;

import java.util.Arrays;

/**
 * 订单类型
 *
 * @author kaaass
 */
public enum OrderType {
    /**
     * 已创建
     */
    CREATED(0),

    /**
     * 已付款
     */
    PAID(1),

    /**
     * 已发货
     */
    DELIVERED(2),

    /**
     * 已评价
     */
    COMMENTED(3),

    /**
     * 已退款
     */
    REFUNDED(4),

    /**
     * 已取消
     */
    CANCELED(5),

    /**
     * 创建错误
     */
    ERROR(6);

    private final int order;

    OrderType(int order) {
        this.order = order;
    }

    public static Optional<OrderType> getTypeById(final int id) {
        return StreamSupport.stream(Arrays.asList(values()))
                .filter(new Predicate<OrderType>() {
                    @Override
                    public boolean test(OrderType type) {
                        return type.order == id;
                    }
                })
                .findAny();
    }

    public boolean less(OrderType type) {
        return order < type.order;
    }

    public boolean great(OrderType type) {
        return order > type.order;
    }
}
