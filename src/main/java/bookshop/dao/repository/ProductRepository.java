package bookshop.dao.repository;

import bookshop.dao.BaseRepository;
import bookshop.dao.entity.CategoryEntity;
import bookshop.dao.entity.ProductEntity;

import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * 产品 DAO
 *
 * @author kaaass
 */
@Stateless
public class ProductRepository extends BaseRepository<ProductEntity, String> {

    public List<ProductEntity> findAllByCategoryIn(Collection<CategoryEntity> category) {
        String sql = "SELECT u FROM ProductEntity u where u.category in ?1";
        return findAllBySql(sql, ProductEntity.class, category);
    }

    public List<ProductEntity> findAllByIndexOrderGreaterThanEqualOrderByIndexOrderAscCreateTimeDesc() {
        String sql = "SELECT u FROM ProductEntity u order by u.indexOrder asc, u.createTime desc";
        return findAllBySql(sql, ProductEntity.class);
    }

    public List<ProductEntity> findAllByNameIsLikeOrderByIndexOrderAscCreateTimeDesc(String name) {
        String sql = "SELECT u FROM ProductEntity u where u.name like ?1 order by u.createTime desc";
        return findAllBySql(sql, ProductEntity.class, name);
    }

    public List<ProductEntity> findAllByStartSellTimeGreaterThanOrderByIndexOrderAscCreateTimeDesc(Timestamp startSellTime) {
        String sql = "SELECT u FROM ProductEntity u where u.startSellTime > ?1 order by u.createTime desc";
        return findAllBySql(sql, ProductEntity.class, startSellTime);
    }

    public List<ProductEntity> searchByIsbn(String isbn) {
        String sql = "SELECT u FROM ProductEntity u where u.isbn = ?1 order by u.createTime desc";
        return findAllBySql(sql, ProductEntity.class, isbn);
    }

    public List<ProductEntity> searchByAuthor(String author) {
        String sql = "SELECT u FROM ProductEntity u where u.author like ?1 order by u.createTime desc";
        return findAllBySql(sql, ProductEntity.class, author);
    }

    public List<ProductEntity> searchByPublishDate(Timestamp start, Timestamp end) {
        String sql = "SELECT u FROM ProductEntity u where u.publishDate between ?1 and ?2 order by u.createTime desc";
        return findAllBySql(sql, ProductEntity.class, start, end);
    }

    public List<ProductEntity> searchByPrice(float low, float high) {
        String sql = "SELECT u FROM ProductEntity u where u.price between ?1 and ?2 order by u.createTime desc";
        return findAllBySql(sql, ProductEntity.class, low, high);
    }
}
