package net.kaaass.bookshop.promote.strategy.dbms;

import java8.util.function.BiFunction;
import java8.util.function.BinaryOperator;
import java8.util.function.Function;
import java8.util.function.Predicate;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.kaaass.bookshop.dao.entity.CategoryEntity;
import net.kaaass.bookshop.dto.PromoteStrategyDto;
import net.kaaass.bookshop.exception.BaseException;
import net.kaaass.bookshop.promote.BaseDbmsPromoteStrategy;
import net.kaaass.bookshop.promote.OrderPromoteContext;
import net.kaaass.bookshop.promote.PromoteItem;
import net.kaaass.bookshop.promote.ServiceAdapter;
import net.kaaass.bookshop.util.NumericUtils;

import java.util.List;

/**
 * 满减策略
 */
@Slf4j
public class DiscountEveryStrategy extends BaseDbmsPromoteStrategy<OrderPromoteContext, OrderPromoteContext> {

    /**
     * 参数
     */
    @Data
    private static class Param {

        /**
         * 作用对象品类
         * <p>
         * 若为null则为通配
         */
        private String categoryId;

        /**
         * 满
         */
        private float every;

        /**
         * 减
         */
        private float discount;

        /**
         * 最大打折次数
         * <p>
         * 若小于零则无限制
         */
        private int maxCount;
    }

    private Param param = null;

    private List<String> categories = null;

    @Override
    protected void initialize(PromoteStrategyDto promoteStrategyDto, ServiceAdapter serviceAdapter) throws BaseException {
        val paramStr = promoteStrategyDto.getParam();
        this.param = parseJsonParam(paramStr, Param.class);
        // 获取所有子分类
        if (param.categoryId != null) {
            val categoryService = serviceAdapter.getCategoryService();
            val category = categoryService.getEntityById(param.categoryId);
            this.categories = StreamSupport.stream(categoryService.getAllSubs(category))
                    .map(new Function<CategoryEntity, String>() {
                        @Override
                        public String apply(CategoryEntity categoryEntity) {
                            return categoryEntity.getId();
                        }
                    })
                    .collect(Collectors.<String>toList());
        }
    }

    @Override
    public Result<OrderPromoteContext> doPromote(OrderPromoteContext context) {
        float allPrice = context.getPrice();
        float filteredPrice = StreamSupport.stream(context.getProducts())
                .filter(new Predicate<PromoteItem>() {
                    @Override
                    public boolean test(PromoteItem promoteItem) {
                        return categories == null
                                || categories.contains(promoteItem.getProduct().getCategory().getId());
                    }
                })
                .reduce(0F, new BiFunction<Float, PromoteItem, Float>() {
                    @Override
                    public Float apply(Float acc, PromoteItem el) {
                        return acc + el.getPrice();
                    }
                }, new BinaryOperator<Float>() {
                    @Override
                    public Float apply(Float aFloat, Float aFloat2) {
                        return aFloat + aFloat2;
                    }
                });
        log.info("筛选可打折价格 {}，品类 {}", filteredPrice, this.categories);
        if (filteredPrice <= 0) {
            return new Result<>(ResultType.NOT_MATCH);
        }
        // 打折次数
        int discountTimes = (int) (filteredPrice / param.every);
        if (param.maxCount >= 0) {
            discountTimes = Math.min(param.maxCount, discountTimes);
        }
        if (discountTimes <= 0) {
            return new Result<>(ResultType.NOT_COND, context);
        }
        // 计算折扣
        float discount = NumericUtils.moneyRound(discountTimes * param.discount);
        context.setPrice(allPrice - discount);
        return new Result<>(ResultType.OK, context);
    }
}
