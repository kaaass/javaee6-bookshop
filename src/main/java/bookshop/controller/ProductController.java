package bookshop.controller;

import bookshop.controller.page.PageInfo;
import bookshop.controller.request.MetadataRequest;
import bookshop.controller.request.ProductAddRequest;
import bookshop.controller.response.ProductCommentResponse;
import bookshop.dao.Pageable;
import bookshop.dto.ProductDto;
import bookshop.exception.BadRequestException;
import bookshop.exception.InternalErrorExeption;
import bookshop.exception.NotFoundException;
import bookshop.security.Secured;
import bookshop.security.SecurityIdentity;
import bookshop.security.SecurityRole;
import bookshop.service.ProductService;
import bookshop.service.metadata.MetadataManager;
import bookshop.vo.ProductExtraVo;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Path("/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductController extends BaseController {

    @Inject
    private ProductService productService;

    @Inject
    private MetadataManager metadataManager;

    @Inject
    private Validator validator;

    @Inject
    private PageInfo pageInfo;

    @Inject
    private SecurityIdentity identity;

    @POST
    @Path("/")
    @Secured(SecurityRole.ADMIN)
    public ProductDto addProduct(ProductAddRequest productDto) throws BadRequestException, NotFoundException {
        validateBean(validator, productDto);
        return productService.addProduct(productDto);
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
    public boolean removeProduct(@PathParam("id") String id) {
        productService.removeProduct(id);
        return true;
    }

    @GET
    @Path("/{id}/")
    public ProductDto getProductById(@PathParam("id") String id) throws NotFoundException {
        return productService.getById(id);
    }

    @PUT
    @Path("/cache/")
    @Secured(SecurityRole.ADMIN)
    public ProductDto addProductCache(ProductAddRequest request) throws NotFoundException, BadRequestException {
        validateBean(validator, request);
        return productService.addProductCache(request);
    }

    @POST
    @Path("/cache/{fakeId}/")
    @Secured(SecurityRole.ADMIN)
    public ProductDto editProductCache(@PathParam("fakeId") String fakeId, ProductAddRequest request) throws NotFoundException, BadRequestException {
        validateBean(validator, request);
        return productService.editProductCache(fakeId, request);
    }

    @DELETE
    @Path("/cache/{fakeId}/")
    @Secured(SecurityRole.ADMIN)
    public boolean removeProductCache(@PathParam("fakeId") String fakeId) throws NotFoundException {
        productService.removeProductCache(fakeId);
        return true;
    }

    @POST
    @Path("/cache/")
    @Secured(SecurityRole.ADMIN)
    public List<ProductDto> commitProductCache() throws BadRequestException {
        return productService.commitProductCache();
    }

    @DELETE
    @Path("/cache/")
    @Secured(SecurityRole.ADMIN)
    public boolean clearProductCache() {
        productService.clearProductCache();
        return true;
    }

    @GET
    @Path("/cache/")
    @Secured(SecurityRole.ADMIN)
    public List<ProductDto> getProductCache() {
        return productService.getProductCache();
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
    @Secured(SecurityRole.LOGGED)
    public List<ProductDto> search(@QueryParam("keyword") String keyword) {
        return productService.search(keyword, pageInfo.getPageable());
    }

    @GET
    @Path("/search/isbn/")
    @Secured(SecurityRole.LOGGED)
    public List<ProductDto> searchByIsbn(@QueryParam("isbn") String isbn, Pageable pageable) {
        return productService.searchByIsbn(isbn, pageInfo.getPageable());
    }

    @GET
    @Path("/search/author/")
    @Secured(SecurityRole.LOGGED)
    public List<ProductDto> searchByAuthor(@QueryParam("author") String author, Pageable pageable) {
        return productService.searchByAuthor(author, pageInfo.getPageable());
    }

    @GET
    @Path("/search/date/")
    @Secured(SecurityRole.LOGGED)
    public List<ProductDto> searchByPublishDate(@QueryParam("start") long start,
                                                @QueryParam("end") long end, Pageable pageable) {
        return productService.searchByPublishDate(new Date(start * 1000), new Date(end * 1000), pageInfo.getPageable());
    }

    @GET
    @Path("/search/price/")
    @Secured(SecurityRole.LOGGED)
    public List<ProductDto> searchByPrice(@QueryParam("low") float low,
                                          @QueryParam("high") float high, Pageable pageable) {
        return productService.searchByPrice(low, high, pageInfo.getPageable());
    }

    @GET
    @Path("/category/{categoryId}/")
    @Secured(SecurityRole.LOGGED)
    public List<ProductDto> getAllProductsByCategory(@PathParam("categoryId") String categoryId) throws NotFoundException {
        return productService.getAllByCategory(categoryId, pageInfo.getPageable());
    }

    @GET
    @Path("/{id}/comments/")
    public ProductCommentResponse getComments(@PathParam("id") String id) {
        // TODO 评论用户头像
        return productService.getComments(id, pageInfo.getPageable());
    }

    @GET
    @Path("/{id}/metadata/")
    @Secured(SecurityRole.ADMIN)
    public Map<String, String> getAllForProduct(@PathParam("id") String productId) {
        return metadataManager.getAllForProduct(productId);
    }

    @POST
    @Path("/{id}/metadata/")
    @Secured(SecurityRole.ADMIN)
    public boolean setForProduct(@PathParam("id") String productId, MetadataRequest request) throws BadRequestException {
        validateBean(validator, request);
        metadataManager.setForProduct(productId, request.getKey(), request.getValue());
        return true;
    }

    @DELETE
    @Path("/{id}/metadata/{key}/")
    @Secured(SecurityRole.ADMIN)
    public boolean deleteForProduct(@PathParam("id") String productId, @PathParam("key") String key) {
        metadataManager.deleteForProduct(productId, key);
        return true;
    }
}
