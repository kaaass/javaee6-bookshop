package bookshop.dao.repository;

import bookshop.dao.BaseRepository;
import bookshop.dao.entity.UserAuthEntity;
import java8.util.Optional;

import javax.ejb.Stateless;

/**
 * 用户鉴权 DAO
 *
 * @author kaaass
 */
@Stateless
public class UserAuthRepository extends BaseRepository<UserAuthEntity, String> {

    /**
     * 通过手机查询
     */
    public Optional<UserAuthEntity> findByPhone(String phone) {
        String sql = "SELECT u FROM UserAuthEntity u where u.phone = ?1";
        return findOneBySql(sql, UserAuthEntity.class, phone);
    }

    /**
     * 通过鉴权令牌查询
     */
    public Optional<UserAuthEntity> findByAuthToken(String authToken) {
        String sql = "SELECT u FROM UserAuthEntity u where u.authToken = ?1";
        return findOneBySql(sql, UserAuthEntity.class, authToken);
    }
}
