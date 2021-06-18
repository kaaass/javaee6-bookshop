package bookshop.service;

import bookshop.controller.request.CommentRequest;
import bookshop.controller.request.OrderCreateRequest;
import bookshop.controller.response.OrderCheckResponse;
import bookshop.controller.response.OrderRequestResponse;
import bookshop.dao.Pageable;
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
