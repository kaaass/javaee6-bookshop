package bookshop.controller;

import bookshop.controller.response.GlobalResponse;
import bookshop.exception.BadRequestException;
import bookshop.exception.BaseException;
import bookshop.security.Secured;
import bookshop.security.SecurityIdentity;
import bookshop.security.SecurityRole;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/test")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestController extends BaseController {

    @Inject
    private SecurityIdentity identity;

    @GET
    public GlobalResponse<List<String>> sayHello() {
        List<String> jsonList = new ArrayList<>();
        jsonList.add("Hello World");
        jsonList.add("Bonjour monde");

        return GlobalResponse.success(jsonList);
    }

    @GET
    @Path("/logged")
    @Secured(SecurityRole.ADMIN)
    public String logged() {
        return "You're logged in! " + identity.getUserAuthDto();
    }

    @GET
    @Path("/exception")
    public GlobalResponse<List<String>> exception() throws BaseException {
        throw new BadRequestException("???");
    }

    @GET
    @Path("/exception2")
    public GlobalResponse<List<String>> exception2() {
        throw new NullPointerException("???");
    }

    @GET
    @Path("/void")
    public void getVoid() {
    }
}
