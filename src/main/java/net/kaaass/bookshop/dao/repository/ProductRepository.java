package net.kaaass.bookshop.dao.repository;

import net.kaaass.bookshop.dao.BaseRepository;
import net.kaaass.bookshop.dao.Pageable;
import net.kaaass.bookshop.dao.entity.CategoryEntity;
import net.kaaass.bookshop.dao.entity.ProductEntity;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * 产品 DAO
 *
 * @author kaaass
 */
public class ProductRepository extends BaseRepository<ProductEntity, String> {

    public List<ProductEntity> findAllByCategoryIn(Collection<CategoryEntity> category, Pageable page) {
        String sql = "SELECT u FROM ProductEntity u where u.category in ?1";
        return findAllBySql(sql, page, ProductEntity.class, category);
    }

    public List<ProductEntity> findAllByIndexOrderGreaterThanEqualOrderByIndexOrderAscCreateTimeDesc(int indexOrder) {
        String sql = "SELECT u FROM ProductEntity u where u.indexOrder >= ?1 order by u.indexOrder asc, u.createTime desc";
        return findAllBySql(sql, ProductEntity.class, indexOrder);
    }

    public List<ProductEntity> findAllByNameIsLikeOrderByIndexOrderAscCreateTimeDesc(String name, Pageable page) {
        String sql = "SELECT u FROM ProductEntity u where u.name like ?1 order by u.indexOrder asc, u.createTime desc";
        return findAllBySql(sql, page, ProductEntity.class, name);
    }

    public List<ProductEntity> findAllByStartSellTimeGreaterThanOrderByIndexOrderAscCreateTimeDesc(Timestamp startSellTime) {
        String sql = "SELECT u FROM ProductEntity u where u.startSellTime > ?1 order by u.indexOrder asc, u.createTime desc";
        return findAllBySql(sql, ProductEntity.class, startSellTime);
    }

    public List<ProductEntity> searchByIsbn(String isbn, Pageable page) {
        String sql = "SELECT u FROM ProductEntity u where u.isbn = ?1 order by u.indexOrder asc, u.createTime desc";
        return findAllBySql(sql, page, ProductEntity.class, isbn);
    }

    public List<ProductEntity> searchByAuthor(String author, Pageable page) {
        String sql = "SELECT u FROM ProductEntity u where u.author like ?1 order by u.indexOrder asc, u.createTime desc";
        return findAllBySql(sql, page, ProductEntity.class, author);
    }

    public List<ProductEntity> searchByPublishDate(Timestamp start, Timestamp end, Pageable page) {
        String sql = "SELECT u FROM ProductEntity u where u.publishDate between ?1 and ?2 order by u.indexOrder asc, u.createTime desc";
        return findAllBySql(sql, page, ProductEntity.class, start, end);
    }

    public List<ProductEntity> searchByPrice(float low, float high, Pageable page) {
        String sql = "SELECT u FROM ProductEntity u where u.price between ?1 and ?2 order by u.indexOrder asc, u.createTime desc";
        return findAllBySql(sql, page, ProductEntity.class, low, high);
    }
}
