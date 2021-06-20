package bookshop.service;

import bookshop.controller.request.ProductAddRequest;
import bookshop.dao.entity.ProductEntity;
import bookshop.dto.ProductDto;
import bookshop.exception.BadRequestException;
import bookshop.exception.InternalErrorExeption;
import bookshop.exception.NotFoundException;
import bookshop.vo.ProductExtraVo;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;

/**
 * 商品服务
 *
 * @author kaaass
 */
@Local
public interface ProductService {

    ProductDto addProduct(ProductAddRequest userToAdd) throws NotFoundException;

    ProductDto editProduct(String id, ProductAddRequest userToAdd) throws NotFoundException, InternalErrorExeption;

    void removeProduct(String id);

    ProductDto getById(String id) throws NotFoundException;

    ProductDto addProductCache(ProductAddRequest userToAdd) throws NotFoundException;

    ProductDto editProductCache(String fakeId, ProductAddRequest request) throws NotFoundException;

    void removeProductCache(String fakeId) throws NotFoundException;

    List<ProductDto> commitProductCache() throws BadRequestException;

    void clearProductCache();

    List<ProductDto> getProductCache();

    /**
     * @deprecated
     */
    ProductEntity getEntityById(String id) throws NotFoundException;

    ProductExtraVo getExtraById(String id, int count, String uid) throws NotFoundException;

    List<ProductDto> getAll();

    List<ProductDto> getIndexItems();

    List<ProductDto> getQuickBuyItems();

    List<ProductDto> getAllByCategory(String categoryId) throws NotFoundException;

    List<ProductDto> search(String keyword);

    List<ProductDto> searchByIsbn(String isbn);

    List<ProductDto> searchByAuthor(String author);

    List<ProductDto> searchByPublishDate(Date start, Date end);

    List<ProductDto> searchByPrice(float low, float high);
}
