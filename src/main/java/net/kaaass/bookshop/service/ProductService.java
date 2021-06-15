package net.kaaass.bookshop.service;

import java.util.Date;
import java.util.List;
import java8.util.Optional;
import net.kaaass.bookshop.controller.request.ProductAddRequest;
import net.kaaass.bookshop.controller.response.ProductCommentResponse;
import net.kaaass.bookshop.dao.Pageable;
import net.kaaass.bookshop.dao.entity.ProductEntity;
import net.kaaass.bookshop.dto.ProductDto;
import net.kaaass.bookshop.exception.InternalErrorExeption;
import net.kaaass.bookshop.exception.NotFoundException;
import net.kaaass.bookshop.vo.ProductExtraVo;

import javax.ejb.Local;

/**
 * 商品服务
 * @author kaaass
 */
@Local
public interface ProductService {

    Optional<ProductDto> addProduct(ProductAddRequest userToAdd);

    ProductDto editProduct(String id, ProductAddRequest userToAdd) throws NotFoundException, InternalErrorExeption;

    void removeProduct(String id);

    ProductDto getById(String id) throws NotFoundException;

    /**
     * @deprecated
     */
    ProductEntity getEntityById(String id) throws NotFoundException;

    ProductExtraVo getExtraById(String id, int count, String uid) throws NotFoundException;

    List<ProductDto> getAll(Pageable pageable);

    List<ProductDto> getIndexItems();

    List<ProductDto> getQuickBuyItems();

    List<ProductDto> getAllByCategory(String categoryId, Pageable pageable) throws NotFoundException;

    ProductCommentResponse getComments(String id, Pageable pageable);

    List<ProductDto> search(String keyword, Pageable pageable);

    List<ProductDto> searchByIsbn(String isbn, Pageable pageable);

    List<ProductDto> searchByAuthor(String author, Pageable pageable);

    List<ProductDto> searchByPublishDate(Date start, Date end, Pageable pageable);

    List<ProductDto> searchByPrice(float low, float high, Pageable pageable);
}