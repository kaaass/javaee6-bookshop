package net.kaaass.bookshop.service.impl;

import java8.util.function.Function;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.kaaass.bookshop.dao.entity.ProductEntity;
import net.kaaass.bookshop.dao.entity.PromoteStrategyEntity;
import net.kaaass.bookshop.dao.repository.PromoteStrategyRepository;
import net.kaaass.bookshop.dto.PromoteStrategyDto;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.exception.BaseException;
import net.kaaass.bookshop.exception.NotFoundException;
import net.kaaass.bookshop.mapper.OrderMapper;
import net.kaaass.bookshop.mapper.ProductMapper;
import net.kaaass.bookshop.promote.*;
import net.kaaass.bookshop.service.ProductService;
import net.kaaass.bookshop.service.PromoteService;
import net.kaaass.bookshop.util.TimeUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
@Slf4j
public class PromoteServiceImpl implements PromoteService, Serializable {

    @Inject
    private PromoteStrategyRepository promoteStrategyRepository;

    @Inject
    private PromoteManager promoteManager;

    @Inject
    private OrderPromoteContextFactory contextFactory;

    @Inject
    private DbmsPromoteStrategyManager dbmsPromoteStrategyManager;

    @Inject
    private ServiceAdapter serviceAdapter;

    @Inject
    private OrderMapper orderMapper;

    @Inject
    private ProductMapper productMapper;

    @Override
    public PromoteStrategyDto getById(String promoteId) throws NotFoundException {
        return orderMapper.promoteStrategyEntitiyToDto(getEntityById(promoteId));
    }

    @Override
    public PromoteStrategyEntity getEntityById(String promoteId) throws NotFoundException {
        return promoteStrategyRepository.findById(promoteId)
                .orElseThrow(BaseException.supplier(NotFoundException.class, "未找到此促销策略！"));
    }

    @Override
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

    @Override
    public PromoteStrategyDto modify(PromoteStrategyDto promoteStrategyDto) {
        val entity = orderMapper.promoteStrategyDtoToEntitiy(promoteStrategyDto);
        entity.setLastUpdateTime(TimeUtils.nowTimestamp());
        val result = promoteStrategyRepository.save(entity);
        return orderMapper.promoteStrategyEntitiyToDto(result);
    }

    @Override
    public void checkConfigure(String promoteId) throws NotFoundException, BadRequestException {
        val strategyDto = getById(promoteId);
        dbmsPromoteStrategyManager.createFromDbms(strategyDto, serviceAdapter);
    }

    @Override
    public OrderPromoteResult getForSingleProduct(ProductEntity productEntity, int count, String uid, String addressId) throws NotFoundException {
        val productDto = productMapper.productEntityToDto(productEntity);
        val context = contextFactory.buildFromSingleProduct(productDto, count, uid, addressId);
        return promoteManager.doOnOrder(context);
    }

    @Override
    public void deleteById(String promoteId) {
        promoteStrategyRepository.deleteById(promoteId);
    }
}
