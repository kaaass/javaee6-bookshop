package net.kaaass.bookshop.controller;

import net.kaaass.bookshop.dto.UserInfoDto;
import net.kaaass.bookshop.security.Secured;
import net.kaaass.bookshop.security.SecurityRole;
import net.kaaass.bookshop.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController extends BaseController {

    @Inject
    private UserService userService;

    @GET
    @Path("/")
    @Secured(SecurityRole.ADMIN)
    public List<UserInfoDto> getAllUser() {
        return userService.getAllUser();
    }
}
