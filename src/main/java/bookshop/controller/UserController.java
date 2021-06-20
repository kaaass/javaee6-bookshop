package bookshop.controller;

import bookshop.security.Secured;
import bookshop.security.SecurityRole;
import bookshop.service.UserService;
import bookshop.vo.UserAuthVo;

import javax.ejb.EJB;
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

    @EJB
    private UserService userService;

    @GET
    @Path("/")
    @Secured(SecurityRole.ADMIN)
    public List<UserAuthVo> getAllUser() {
        return userService.getAllUser();
    }
}
