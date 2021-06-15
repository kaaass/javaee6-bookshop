package net.kaaass.bookshop.controller;

import net.kaaass.bookshop.controller.page.PageInfo;
import net.kaaass.bookshop.controller.request.ProductAddRequest;
import net.kaaass.bookshop.controller.response.ProductCommentResponse;
import net.kaaass.bookshop.dto.ProductDto;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.exception.InternalErrorExeption;
import net.kaaass.bookshop.exception.NotFoundException;
import net.kaaass.bookshop.security.Secured;
import net.kaaass.bookshop.security.SecurityIdentity;
import net.kaaass.bookshop.security.SecurityRole;
import net.kaaass.bookshop.service.ProductService;
import net.kaaass.bookshop.vo.ProductExtraVo;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductController extends BaseController {

    @Inject
    private ProductService productService;

    @Inject
    private Validator validator;

    @Inject
    private PageInfo pageInfo;

    @Inject
    private SecurityIdentity identity;

    @POST
    @Path("/")
    @Secured(SecurityRole.ADMIN)
    public ProductDto addProduct(ProductAddRequest productDto) throws BadRequestException {
        validateBean(validator, productDto);
        return productService.addProduct(productDto).orElseThrow();
    }

    @POST
    @Path("/{id}/")
    @Secured(SecurityRole.ADMIN)
    public ProductDto editProduct(@PathParam("id") String id, ProductAddRequest productDto) throws NotFoundException, InternalErrorExeption, BadRequestException {
        validateBean(validator, productDto);
        return productService.editProduct(id, productDto);
    }

    @DELETE
    @Path("/{id}/")
    @Secured(SecurityRole.ADMIN)
    public void removeProduct(@PathParam("id") String id) {
        productService.removeProduct(id);
    }

    @GET
    @Path("/{id}/")
    public ProductDto getProductById(@PathParam("id") String id) throws NotFoundException {
        return productService.getById(id);
    }

    @GET
    @Path("/{id}/extra/")
    public ProductExtraVo getExtraById(@PathParam("id") String id, @QueryParam("count") int count) throws NotFoundException {
        String uid = null;
        try {
            uid = getUid(identity);
        } catch (Exception ignored) {
        }
        return productService.getExtraById(id, count, uid);
    }

    @GET
    @Path("/")
    public List<ProductDto> getAllProducts() {
        return productService.getAll(pageInfo.getPageable());
    }

    @GET
    @Path("/index/")
    public List<ProductDto> getIndexItems() {
        return productService.getIndexItems();
    }

    @GET
    @Path("/quick/")
    public List<ProductDto> getQuickBuyItems() {
        return productService.getQuickBuyItems();
    }

    @GET
    @Path("/search/")
    public List<ProductDto> search(@QueryParam("keyword") String keyword) {
        return productService.search(keyword, pageInfo.getPageable());
    }

    @GET
    @Path("/category/{categoryId}/")
    public List<ProductDto> getAllProductsByCategory(@PathParam("categoryId") String categoryId) throws NotFoundException {
        return productService.getAllByCategory(categoryId, pageInfo.getPageable());
    }

    @GET
    @Path("/{id}/comments/")
    public ProductCommentResponse getComments(@PathParam("id") String id) {
        return productService.getComments(id, pageInfo.getPageable());
    }
}
