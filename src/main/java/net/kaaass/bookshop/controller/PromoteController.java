package net.kaaass.bookshop.controller;

import net.kaaass.bookshop.dto.PromoteStrategyDto;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.exception.NotFoundException;
import net.kaaass.bookshop.security.Secured;
import net.kaaass.bookshop.security.SecurityRole;
import net.kaaass.bookshop.service.PromoteService;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/promote")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PromoteController extends BaseController {

    @Inject
    private PromoteService promoteService;

    @Inject
    private Validator validator;

    @GET
    @Path("/{promoteId}/")
    @Secured(SecurityRole.ADMIN)
    public PromoteStrategyDto getById(@PathParam("promoteId") String promoteId) throws NotFoundException {
        return this.promoteService.getById(promoteId);
    }

    @GET
    @Path("/")
    @Secured(SecurityRole.ADMIN)
    public List<PromoteStrategyDto> getAll() {
        return this.promoteService.getAll();
    }

    @POST
    @Path("/")
    @Secured(SecurityRole.ADMIN)
    public PromoteStrategyDto modify(PromoteStrategyDto promoteStrategyDto) throws BadRequestException {
        validateBean(validator, promoteStrategyDto);
        return this.promoteService.modify(promoteStrategyDto);
    }

    @GET
    @Path("/{promoteId}/check/")
    @Secured(SecurityRole.ADMIN)
    public void checkConfigure(@PathParam("promoteId") String promoteId) throws NotFoundException, BadRequestException {
        promoteService.checkConfigure(promoteId);
    }

    @DELETE
    @Path("/{promoteId}/")
    @Secured(SecurityRole.ADMIN)
    public void deleteById(@PathParam("promoteId") String promoteId) throws NotFoundException {
        this.promoteService.deleteById(promoteId);
    }
}
