package bookshop.controller;

import bookshop.controller.request.CategoryAddRequest;
import bookshop.dto.CategoryDto;
import bookshop.exception.BadRequestException;
import bookshop.exception.NotFoundException;
import bookshop.security.Secured;
import bookshop.security.SecurityRole;
import bookshop.service.CategoryService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/category")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryController extends BaseController {

    @EJB
    private CategoryService categoryService;

    @Inject
    private Validator validator;

    @POST
    @Path("/")
    @Secured(SecurityRole.ADMIN)
    public CategoryDto addCategory(CategoryAddRequest categoryDto) throws BadRequestException {
        validateBean(validator, categoryDto);
        return categoryService.add(categoryDto).orElseThrow();
    }

    @GET
    @Path("/{id}/")
    public CategoryDto getCategoryById(@PathParam("id") String id) throws NotFoundException {
        return categoryService.getById(id);
    }

    @DELETE
    @Path("/{id}/")
    @Secured(SecurityRole.ADMIN)
    public boolean deleteCategoryById(@PathParam("id") String id) throws NotFoundException {
        categoryService.deleteById(id);
        return true;
    }

    @GET
    @Path("/")
    public List<CategoryDto> getAll() {
        return categoryService.getAll();
    }
}
