package net.kaaass.bookshop.controller;

import net.kaaass.bookshop.controller.page.PageInfo;
import net.kaaass.bookshop.controller.request.CartAddRequest;
import net.kaaass.bookshop.dto.CartDto;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.exception.ForbiddenException;
import net.kaaass.bookshop.exception.NotFoundException;
import net.kaaass.bookshop.security.Secured;
import net.kaaass.bookshop.security.SecurityIdentity;
import net.kaaass.bookshop.security.SecurityRole;
import net.kaaass.bookshop.service.CartService;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user/cart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserCartController extends BaseController {

    @Inject
    private CartService cartService;

    @Inject
    private Validator validator;

    @Inject
    private SecurityIdentity identity;

    @Inject
    private PageInfo pageInfo;

    @POST
    @Path("/")
    @Secured(SecurityRole.USER)
    public CartDto addToCart(CartAddRequest request) throws NotFoundException, BadRequestException {
        validateBean(validator, request);
        return cartService.addToCart(getUid(identity), request);
    }

    @DELETE
    @Path("/{id}/")
    @Secured(SecurityRole.USER)
    public void removeFromCart(@PathParam("id") String id) throws NotFoundException, ForbiddenException {
        cartService.removeFromCart(getUid(identity), id);
    }

    @POST
    @Path("/{id}/count/")
    @Secured(SecurityRole.USER)
    public CartDto modifyItemCount(@PathParam("id") String id, @QueryParam("count") int count) throws BadRequestException, NotFoundException, ForbiddenException {
        if (count < 1) {
            throw new BadRequestException("数量必须大于等于1！");
        }
        return cartService.modifyItemCount(getUid(identity), id, count);
    }

    @GET
    @Path("/")
    @Secured(SecurityRole.USER)
    public List<CartDto> getAllByUid() {
        return cartService.getAllByUid(getUid(identity), pageInfo.getPageable());
    }
}
