package bookshop.dao.repository;

import bookshop.dao.BaseRepository;
import bookshop.dao.entity.UserAddressEntity;
import java8.util.Optional;

import javax.ejb.Stateless;
import java.util.List;

/**
 * 用户地址 DAO
 *
 * @author kaaass
 */
@Stateless
public class UserAddressRepository extends BaseRepository<UserAddressEntity, String> {

    public List<UserAddressEntity> findAllByUid(String uid) {
        String sql = "SELECT u FROM UserAddressEntity u where u.user.id = ?1";
        return findAllBySql(sql, UserAddressEntity.class, uid);
    }

    public Optional<UserAddressEntity> findFirstByUidAndDefaultAddressTrue(String uid) {
        String sql = "select c from UserAddressEntity c where c.user.id = ?1 and c.defaultAddress = true";
        return findOneBySql(sql, UserAddressEntity.class, uid);
    }
}
