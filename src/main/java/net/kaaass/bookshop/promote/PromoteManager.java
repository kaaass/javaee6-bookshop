package net.kaaass.bookshop.promote;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.exception.NotFoundException;
import net.kaaass.bookshop.promote.collector.CommonCollector;
import net.kaaass.bookshop.promote.collector.ViewCollector;
import net.kaaass.bookshop.promote.strategy.CommonDiscountStrategy;
import net.kaaass.bookshop.promote.strategy.CommonMailFreeStrategy;
import net.kaaass.bookshop.promote.strategy.CommonPriceStrategy;
import net.kaaass.bookshop.service.metadata.MetadataManager;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Slf4j
public class PromoteManager {

    @Inject
    private MetadataManager metadataManager;

    @Inject
    private DbmsPromoteStrategyManager dbmsPromoteStrategyManager;

    @Inject
    private ServiceAdapter serviceAdapter;

    private PromoteFlow<OrderPromoteContext> packWithDbms(PromoteFlow<OrderPromoteContext> flow) throws NotFoundException, BadRequestException {
        val strategies = dbmsPromoteStrategyManager.getAll();
        log.info("得到打折策略：{}", strategies);
        PromoteFlow genericLessFlow = flow;
        for (val strategy : strategies) {
            genericLessFlow = genericLessFlow.on(dbmsPromoteStrategyManager.createFromDbms(strategy, serviceAdapter));
        }
        return genericLessFlow;
    }

    /**
     * 从数据库构建打折流执行器
     *
     * @param forView
     * @return 打折流执行器
     */
    private PromoteExecutor buildExecutorFromDbms(boolean forView) {
        PromoteFlow<OrderPromoteContext> flow = PromoteFlow.start();
        // 基础打折策略最先
        flow = flow
                .on(CommonPriceStrategy.INSTANCE) // 必须最前
                .on(new CommonDiscountStrategy(metadataManager))
                .on(CommonMailFreeStrategy.INSTANCE);
        // 从数据库读取策略
        try {
            flow = packWithDbms(flow);
        } catch (NotFoundException | BadRequestException e) {
            log.warn("从数据库构建策略失败！", e);
        }
        // Pipeline Valve
        return flow.collect(forView ? ViewCollector.INSTANCE : CommonCollector.INSTANCE);
    }

    /**
     * 对某个订单进行处理
     *
     * @param context 订单上下文
     * @return 折扣结果
     */
    public OrderPromoteResult doOnOrder(OrderPromoteContext context) {
        val executor = buildExecutorFromDbms(context.isForView());
        return executor.execute(context);
    }
}
