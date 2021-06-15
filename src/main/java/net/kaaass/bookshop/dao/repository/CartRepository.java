package net.kaaass.bookshop.dao.repository;

import java.util.List;
import java8.util.Optional;
import net.kaaass.bookshop.dao.BaseRepository;
import net.kaaass.bookshop.dao.Pageable;
import net.kaaass.bookshop.dao.entity.CartEntity;
import net.kaaass.bookshop.dao.entity.CategoryEntity;
import net.kaaass.bookshop.dao.entity.ProductEntity;

public class CartRepository extends BaseRepository<CartEntity, String> {

    public List<CartEntity> findAllByUidOrderByCreateTimeDesc(String uid, Pageable pageable) {
        String sql = "SELECT u FROM CartEntity u where u.uid = ?1";
        return findAllBySql(sql, pageable, CartEntity.class, uid);
    }

    public Optional<CartEntity> findByProductAndUid(ProductEntity product, String uid) {
        String sql = "select c from CartEntity c where c.product = ?1 and c.uid = ?2";
        return findOneBySql(sql, CartEntity.class, product, uid);
    }
}
