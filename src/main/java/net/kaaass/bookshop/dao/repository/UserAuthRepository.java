package net.kaaass.bookshop.dao.repository;

import java8.util.Optional;
import net.kaaass.bookshop.dao.BaseRepository;
import net.kaaass.bookshop.dao.entity.UserAuthEntity;

/**
 * 用户鉴权 DAO
 *
 * @author kaaass
 */
public class UserAuthRepository extends BaseRepository<UserAuthEntity, String> {

    /**
     * 通过手机查询
     */
    public Optional<UserAuthEntity> findByPhone(String phone) {
        String sql = "SELECT u FROM UserAuthEntity u where u.phone = ?0";
        return findOneBySql(sql, UserAuthEntity.class, phone);
    }

    /**
     * 通过鉴权令牌查询
     */
    public Optional<UserAuthEntity> findByAuthToken(String authToken) {
        String sql = "SELECT u FROM UserAuthEntity u where u.authToken = :authToken";
        return findOneBySql(sql, UserAuthEntity.class, authToken);
    }
}
