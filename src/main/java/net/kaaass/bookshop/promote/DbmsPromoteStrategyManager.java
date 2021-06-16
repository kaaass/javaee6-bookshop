package net.kaaass.bookshop.promote;

import java8.util.function.Function;
import java8.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java8.util.stream.Collectors;
import lombok.val;
import net.kaaass.bookshop.dao.entity.PromoteStrategyEntity;
import net.kaaass.bookshop.dao.repository.PromoteStrategyRepository;
import net.kaaass.bookshop.dto.PromoteStrategyDto;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.exception.BaseException;
import net.kaaass.bookshop.exception.NotFoundException;
import net.kaaass.bookshop.mapper.OrderMapper;

/**
 * 管理数据库策略
 */
@Stateless
@Slf4j
public class DbmsPromoteStrategyManager {

    @Inject
    private PromoteStrategyRepository promoteStrategyRepository;

    @Inject
    private OrderMapper orderMapper;

    /**
     * 依照优先级获得策略顺序
     * @return 策略s
     */
    public List<PromoteStrategyDto> getAll() {
        return StreamSupport.stream(promoteStrategyRepository.findAllByEnabledTrueOrderByOrderAscLastUpdateTimeDesc())
                .map(new Function<PromoteStrategyEntity, PromoteStrategyDto>() {
                    @Override
                    public PromoteStrategyDto apply(PromoteStrategyEntity promoteStrategyEntity) {
                        return orderMapper.promoteStrategyEntitiyToDto(promoteStrategyEntity);
                    }
                })
                .collect(Collectors.<PromoteStrategyDto>toList());
    }

    /**
     * 使用反射，从数据库创建策略
     * @param promoteStrategyDto 策略DTO
     * @param serviceAdapter 服务适配器
     * @return 策略
     */
    public BaseDbmsPromoteStrategy createFromDbms(PromoteStrategyDto promoteStrategyDto, ServiceAdapter serviceAdapter) throws NotFoundException, BadRequestException {
        try {
            val clazz = Class.forName(promoteStrategyDto.getClazz());
            if (!BaseDbmsPromoteStrategy.class.isAssignableFrom(clazz)) { // 需继承BaseDbmsPromoteStrategy
                throw new BadRequestException("该策略不合法！");
            }
            val constructor = clazz.getConstructor();
            BaseDbmsPromoteStrategy strategy = (BaseDbmsPromoteStrategy) constructor.newInstance();
            strategy.initialize(promoteStrategyDto, serviceAdapter);
            strategy.promoteStrategyInfoVo = orderMapper.promoteStrategyDtoToInfoVo(promoteStrategyDto);
            return strategy;
        } catch (ClassNotFoundException e) {
            log.info("找不到策略类：", e);
            throw new NotFoundException("找不到该策略！");
        } catch (NoSuchMethodException e) {
            log.info("该策略类无默认构造方法：", e);
            throw new BadRequestException("该策略编写有误！");
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            log.info("无法构造策略对象：", e);
            throw new BadRequestException("该策略编写有误！");
        } catch (BaseException e) {
            log.info("策略参数错误：", e);
            throw new BadRequestException("策略参数错误！");
        }
    }
}
