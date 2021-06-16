package net.kaaass.bookshop.service;

import net.kaaass.bookshop.controller.request.CommentRequest;
import net.kaaass.bookshop.controller.request.OrderCreateRequest;
import net.kaaass.bookshop.controller.response.OrderCheckResponse;
import net.kaaass.bookshop.controller.response.OrderRequestResponse;
import net.kaaass.bookshop.dao.Pageable;
import net.kaaass.bookshop.dao.entity.OrderEntity;
import net.kaaass.bookshop.dto.OrderDto;
import net.kaaass.bookshop.dto.OrderType;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.exception.ForbiddenException;
import net.kaaass.bookshop.exception.InternalErrorExeption;
import net.kaaass.bookshop.exception.NotFoundException;
import net.kaaass.bookshop.vo.UserOrderCountVo;

import javax.ejb.Local;
import java.util.List;

/**
 * 订单服务
 *
 * @author kaaass
 */
@Local
public interface OrderService {

    /**
     * @deprecated
     */
    OrderEntity getEntityById(String id) throws NotFoundException;

    /**
     * @deprecated
     */
    OrderEntity getEntityByIdAndCheck(String id, String uid) throws NotFoundException, ForbiddenException;

    OrderCheckResponse checkRequest(String requestId) throws BadRequestException;

    OrderDto getById(String id) throws NotFoundException;

    OrderDto getByIdAndCheck(String id, String uid) throws NotFoundException, ForbiddenException;

    List<OrderDto> getAll(Pageable pageable);

    List<OrderDto> getAllByUid(String uid, Pageable pageable);

    UserOrderCountVo getUserOrderCount(String uid);

    List<OrderDto> getAllByUidAndType(String uid, OrderType type, Pageable pageable);

    List<OrderDto> getAllByType(OrderType type, Pageable pageable);

    List<OrderDto> getAllByProduct(String pid, Pageable pageable) throws NotFoundException;

    OrderRequestResponse createToQueue(String uid, OrderCreateRequest request) throws InternalErrorExeption, NotFoundException;

    void doCreate(OrderRequestContext context) throws NotFoundException;

    OrderDto setPaid(String id, String uid) throws NotFoundException, ForbiddenException, BadRequestException;

    OrderDto setDelivered(String id, String deliverCode) throws NotFoundException, BadRequestException;

    OrderDto setCanceled(String id, String uid) throws NotFoundException, ForbiddenException, BadRequestException;

    OrderDto setRefunded(String id) throws NotFoundException, BadRequestException;

    OrderDto setCommented(String id, String uid, CommentRequest commentRequest) throws NotFoundException, ForbiddenException, BadRequestException;
}
