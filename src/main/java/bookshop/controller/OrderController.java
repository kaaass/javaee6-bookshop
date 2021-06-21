package bookshop.controller;

import bookshop.controller.request.OrderCreateRequest;
import bookshop.controller.response.OrderCheckResponse;
import bookshop.controller.response.OrderRequestResponse;
import bookshop.dto.OrderDto;
import bookshop.dto.OrderType;
import bookshop.exception.*;
import bookshop.security.Secured;
import bookshop.security.SecurityIdentity;
import bookshop.security.SecurityRole;
import bookshop.service.OrderService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/order")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderController extends BaseController {

    @Inject
    private OrderService orderService;

    @Inject
    private Validator validator;

    @Inject
    private SecurityIdentity identity;

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
        return orderService.getAllByUid(identity.getUserAuthDto().getId());
    }

    @GET
    @Path("/admin/")
    @Secured(SecurityRole.ADMIN)
    public List<OrderDto> getAll() {
        return orderService.getAll();
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
        return orderService.getAllByUidAndType(identity.getUserAuthDto().getId(), type);
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
        return orderService.getAllByType(type);
    }

    @GET
    @Path("/admin/product/{productId}/")
    @Secured(SecurityRole.ADMIN)
    public List<OrderDto> getAllByProduct(@PathParam("productId") String pid) throws NotFoundException {
        return orderService.getAllByProduct(pid);
    }

    @POST
    @Path("/{id}/deliver/")
    @Secured(SecurityRole.ADMIN)
    public OrderDto setDelivered(@PathParam("id") String id, @QueryParam("deliverCode") String deliverCode) throws NotFoundException, BadRequestException {
        return orderService.setDelivered(id, deliverCode);
    }

    @POST
    @Path("/{id}/cancel/")
    @Secured(SecurityRole.USER)
    public OrderDto setCanceled(@PathParam("id") String id) throws BadRequestException, NotFoundException, ForbiddenException {
        return orderService.setCanceled(id, identity.getUserAuthDto().getId());
    }
}
