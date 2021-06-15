package net.kaaass.bookshop.dao.repository;

import java8.util.Optional;
import net.kaaass.bookshop.dao.BaseRepository;
import net.kaaass.bookshop.dao.entity.UserAddressEntity;

import java.util.List;

/**
 * 用户地址 DAO
 * @author kaaass
 */
public class UserAddressRepository extends BaseRepository<UserAddressEntity, String> {

    public List<UserAddressEntity> findAllByUid(String uid) {
        String sql = "SELECT u FROM UserAddressEntity u where u.uid = ?1";
        return findAllBySql(sql, UserAddressEntity.class, uid);
    }

    public Optional<UserAddressEntity> findFirstByUidAndDefaultAddressTrue(String uid) {
        String sql = "select c from UserAddressEntity c where c.uid = ?1 and c.defaultAddress = true";
        return findOneBySql(sql, UserAddressEntity.class, uid);
    }
}
