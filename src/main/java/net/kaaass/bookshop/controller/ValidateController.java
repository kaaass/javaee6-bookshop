package net.kaaass.bookshop.controller;

import net.kaaass.bookshop.constraints.IsbnValidator;
import net.kaaass.bookshop.exception.BadRequestException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 校验控制器
 * @author kaaass
 */
@Path("/validate")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ValidateController extends BaseController {

    @GET
    @Path("/isbn")
    public String validateIsbn(@QueryParam("id") String id) throws BadRequestException {
        return IsbnValidator.format(id);
    }
}
