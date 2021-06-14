package net.kaaass.bookshop.dao.repository;

import java.util.List;
import java8.util.Optional;
import net.kaaass.bookshop.dao.BaseRepository;
import net.kaaass.bookshop.dao.Pageable;
import net.kaaass.bookshop.dao.entity.CartEntity;
import net.kaaass.bookshop.dao.entity.ProductEntity;

public class CartRepository extends BaseRepository<CartEntity, String> {

    public List<CartEntity> findAllByUidOrderByCreateTimeDesc(String uid, Pageable pageable) {
        // TODO
        return null;
    }

    public Optional<CartEntity> findByProductAndUid(ProductEntity product, String uid) {
        // TODO
        return null;
    }
}
