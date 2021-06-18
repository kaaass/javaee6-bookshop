package net.kaaass.bookshop.service;

import net.kaaass.bookshop.dao.entity.ProductEntity;
import net.kaaass.bookshop.dao.entity.PromoteStrategyEntity;
import net.kaaass.bookshop.dto.PromoteStrategyDto;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.exception.NotFoundException;
import net.kaaass.bookshop.promote.OrderPromoteResult;

import javax.ejb.Local;
import java.util.List;

/**
 * 打折服务
 *
 * @author kaaass
 */
@Local
public interface PromoteService {

    PromoteStrategyDto getById(String promoteId) throws NotFoundException;

    /**
     * @deprecated
     */
    PromoteStrategyEntity getEntityById(String promoteId) throws NotFoundException;

    List<PromoteStrategyDto> getAll();

    PromoteStrategyDto modify(PromoteStrategyDto promoteStrategyDto);

    void checkConfigure(String promoteId) throws NotFoundException, BadRequestException;

    OrderPromoteResult getForSingleProduct(ProductEntity productEntity, int count, String uid, String addressId) throws NotFoundException;

    void deleteById(String promoteId);
}
