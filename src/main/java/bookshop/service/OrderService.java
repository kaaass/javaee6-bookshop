package bookshop.service;

import bookshop.controller.request.OrderCreateRequest;
import bookshop.controller.response.OrderCheckResponse;
import bookshop.controller.response.OrderRequestResponse;
import bookshop.dao.entity.OrderEntity;
import bookshop.dto.OrderDto;
import bookshop.dto.OrderType;
import bookshop.exception.BadRequestException;
import bookshop.exception.ForbiddenException;
import bookshop.exception.InternalErrorExeption;
import bookshop.exception.NotFoundException;
import bookshop.vo.UserOrderCountVo;

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

    List<OrderDto> getAll();

    List<OrderDto> getAllByUid(String uid);

    UserOrderCountVo getUserOrderCount(String uid);

    List<OrderDto> getAllByUidAndType(String uid, OrderType type);

    List<OrderDto> getAllByType(OrderType type);

    List<OrderDto> getAllByProduct(String pid) throws NotFoundException;

    OrderRequestResponse createToQueue(String uid, OrderCreateRequest request) throws InternalErrorExeption, NotFoundException;

    void doCreate(OrderRequestContext context) throws NotFoundException;

    OrderDto setDelivered(String id, String deliverCode) throws NotFoundException, BadRequestException;

    OrderDto setCanceled(String id, String uid) throws NotFoundException, ForbiddenException, BadRequestException;
}
