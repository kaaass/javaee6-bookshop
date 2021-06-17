package net.kaaass.bookshop.dao.repository;

import java8.util.Optional;
import net.kaaass.bookshop.dao.BaseRepository;
import net.kaaass.bookshop.dao.entity.ProductMetadataEntity;

import java.util.List;

/**
 * 商品元数据 DAO
 *
 * @author kaaass
 */
public class ProductMetadataRepository extends BaseRepository<ProductMetadataEntity, String> {

    public Optional<ProductMetadataEntity> findByProductIdAndKey(String productId, String key) {
        String sql = "select c from ProductMetadataEntity c where c.productId = ?1 and c.key = ?2";
        return findOneBySql(sql, ProductMetadataEntity.class, productId, key);
    }

    public List<ProductMetadataEntity> findAllByProductId(String productId) {
        String sql = "SELECT u FROM ProductMetadataEntity u where u.productId = ?1";
        return findAllBySql(sql, ProductMetadataEntity.class, productId);
    }
}
