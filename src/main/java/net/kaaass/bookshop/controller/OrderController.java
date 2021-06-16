package net.kaaass.bookshop.controller;

import lombok.extern.slf4j.Slf4j;
import net.kaaass.bookshop.controller.page.PageInfo;
import net.kaaass.bookshop.controller.request.CommentRequest;
import net.kaaass.bookshop.controller.request.OrderCreateRequest;
import net.kaaass.bookshop.controller.response.OrderCheckResponse;
import net.kaaass.bookshop.controller.response.OrderRequestResponse;
import net.kaaass.bookshop.dao.Pageable;
import net.kaaass.bookshop.dto.OrderDto;
import net.kaaass.bookshop.dto.OrderType;
import net.kaaass.bookshop.exception.*;
import net.kaaass.bookshop.security.Secured;
import net.kaaass.bookshop.security.SecurityIdentity;
import net.kaaass.bookshop.security.SecurityRole;
import net.kaaass.bookshop.service.OrderService;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/order")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class OrderController extends BaseController {

    @Inject
    private OrderService orderService;

    @Inject
    private Validator validator;

    @Inject
    private SecurityIdentity identity;

    @Inject
    private PageInfo pageInfo;

    @GET
    @Path("/request/{requestId}/")
    @Secured(SecurityRole.USER)
    public OrderCheckResponse checkRequest(@PathParam("requestId") String requestId) throws BadRequestException {
        return orderService.checkRequest(requestId);
    }

    @GET
    @Path("/{id}/")
    @Secured(SecurityRole.USER)
    public OrderDto getById(@PathParam("id") String id) throws NotFoundException, ForbiddenException {
        return orderService.getByIdAndCheck(id, identity.getUserAuthDto().getId());
    }

    @GET
    @Path("/admin/{id}/")
    @Secured(SecurityRole.ADMIN)
    public OrderDto getByIdAdmin(@PathParam("id") String id) throws NotFoundException {
        return orderService.getById(id);
    }

    @GET
    @Path("/")
    @Secured(SecurityRole.USER)
    public List<OrderDto> getAllByUid() {
        return orderService.getAllByUid(identity.getUserAuthDto().getId(), pageInfo.getPageable());
    }

    @GET
    @Path("/admin/")
    @Secured(SecurityRole.ADMIN)
    public List<OrderDto> getAll() {
        return orderService.getAll(pageInfo.getPageable());
    }

    @POST
    @Path("/")
    @Secured(SecurityRole.USER)
    public OrderRequestResponse createToQueue(OrderCreateRequest request) throws NotFoundException, InternalErrorExeption, BadRequestException {
        validateBean(validator, request);
        return orderService.createToQueue(identity.getUserAuthDto().getId(), request);
    }

    @GET
    @Path("/type/{typeId}/")
    @Secured(SecurityRole.USER)
    public List<OrderDto> getAllByUidAndType(@PathParam("typeId") String typeId) throws NotFoundException {
        OrderType type;
        try {
            type = OrderType.getTypeById(Integer.parseInt(typeId))
                    .orElseThrow(BaseException.supplier(NotFoundException.class, "订单类型不存在！"));
        } catch (NumberFormatException e) {
            throw new NotFoundException("订单类型错误！");
        }
        return orderService.getAllByUidAndType(identity.getUserAuthDto().getId(), type, pageInfo.getPageable());
    }

    @GET
    @Path("/admin/type/{typeId}/")
    @Secured(SecurityRole.ADMIN)
    public List<OrderDto> getAllByType(@PathParam("typeId") String typeId) throws NotFoundException {
        OrderType type;
        try {
            type = OrderType.getTypeById(Integer.parseInt(typeId))
                    .orElseThrow(BaseException.supplier(NotFoundException.class, "订单类型不存在！"));
        } catch (NumberFormatException e) {
            throw new NotFoundException("订单类型错误！");
        }
        return orderService.getAllByType(type, pageInfo.getPageable());
    }

    @GET
    @Path("/admin/product/{productId}/")
    @Secured(SecurityRole.ADMIN)
    public List<OrderDto> getAllByProduct(@PathParam("productId") String pid) throws NotFoundException {
        return orderService.getAllByProduct(pid, pageInfo.getPageable());
    }

    /**
     * 外部支付回调
     */
    @GET
    @Path("/{id}/payCheck/")
    @Secured(SecurityRole.USER)
    public OrderDto payCheck(@PathParam("id") String id, @QueryParam("callback") String callback) throws NotFoundException, ForbiddenException, BadRequestException {
        // 检查callback
        return orderService.setPaid(id, identity.getUserAuthDto().getId());
    }

    @POST
    @Path("/{id}/pay/")
    @Secured(SecurityRole.ADMIN)
    public OrderDto setPaid(@PathParam("id") String id) throws NotFoundException, ForbiddenException, BadRequestException {
        return orderService.setPaid(id, null);
    }

    @POST
    @Path("/{id}/deliver/")
    @Secured(SecurityRole.ADMIN)
    public OrderDto setDelivered(@PathParam("id") String id, @QueryParam("deliverCode") String deliverCode) throws NotFoundException, BadRequestException {
        // TODO 检查输入
        return orderService.setDelivered(id, deliverCode);
    }

    @POST
    @Path("/{id}/cancel/")
    @Secured(SecurityRole.USER)
    public OrderDto setCanceled(@PathParam("id") String id) throws BadRequestException, NotFoundException, ForbiddenException {
        return orderService.setCanceled(id, identity.getUserAuthDto().getId());
    }

    @POST
    @Path("/{id}/refund/")
    @Secured(SecurityRole.ADMIN)
    public OrderDto setRefunded(@PathParam("id") String id) throws NotFoundException, BadRequestException {
        return orderService.setRefunded(id);
    }

    @POST
    @Path("/{id}/comment/")
    @Secured(SecurityRole.USER)
    public OrderDto setCommented(@PathParam("id") String id, CommentRequest commentRequest) throws NotFoundException, BadRequestException, ForbiddenException {
        validateBean(validator, commentRequest);
        return orderService.setCommented(id, identity.getUserAuthDto().getId(), commentRequest);
    }
}
