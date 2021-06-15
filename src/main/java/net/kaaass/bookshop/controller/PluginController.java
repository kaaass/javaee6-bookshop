package net.kaaass.bookshop.controller;

import net.kaaass.bookshop.dto.PluginDto;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.exception.NotFoundException;
import net.kaaass.bookshop.security.Secured;
import net.kaaass.bookshop.security.SecurityRole;
import net.kaaass.bookshop.service.PluginService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/plugin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PluginController extends BaseController {

    @Inject
    private PluginService pluginService;

    @GET
    @Path("/")
    @Secured(SecurityRole.ADMIN)
    public List<PluginDto> getAll() {
        return pluginService.getAll();
    }

    @POST
    @Path("/")
    @Secured(SecurityRole.ADMIN)
    public PluginDto enable(@QueryParam("path") String path) throws BadRequestException {
        return pluginService.enable(path);
    }

    @POST
    @Path("/{id}/disable/")
    @Secured(SecurityRole.ADMIN)
    public boolean disable(@PathParam("id") String id) throws NotFoundException, BadRequestException {
        pluginService.disable(id);
        return true;
    }

    @DELETE
    @Path("/{id}/")
    @Secured(SecurityRole.ADMIN)
    public boolean remove(@PathParam("id") String id) throws NotFoundException {
        pluginService.remove(id);
        return true;
    }
}
