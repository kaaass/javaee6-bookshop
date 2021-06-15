package net.kaaass.bookshop.controller;

import net.kaaass.bookshop.controller.page.PageInfo;
import net.kaaass.bookshop.controller.request.CategoryAddRequest;
import net.kaaass.bookshop.dto.CategoryDto;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.exception.NotFoundException;
import net.kaaass.bookshop.security.Secured;
import net.kaaass.bookshop.security.SecurityRole;
import net.kaaass.bookshop.service.CategoryService;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/category")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryController extends BaseController {

    @Inject
    private CategoryService categoryService;

    @Inject
    private Validator validator;

    @Inject
    private PageInfo pageInfo;

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

    @GET
    @Path("/")
    public List<CategoryDto> getAllProducts() {
        return categoryService.getAll(pageInfo.getPageable());
    }
}
