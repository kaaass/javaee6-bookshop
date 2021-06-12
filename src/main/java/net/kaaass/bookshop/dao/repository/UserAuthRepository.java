package net.kaaass.bookshop.dao.repository;

import java8.util.Optional;
import lombok.val;
import net.kaaass.bookshop.dao.entity.UserAuthEntity;

/**
 * 用户鉴权 DAO
 *
 * @author kaaass
 */
public class UserAuthRepository extends BaseRepository<UserAuthEntity, String> {

    public Optional<UserAuthEntity> findByPhone(String phone) {
        val manager = getEntityManager();
        // 通过手机号查询
        val sql = "SELECT u FROM UserAuthEntity u where u.phone = :phone";
        val query = manager.createQuery(sql, UserAuthEntity.class);
        query.setParameter("phone", phone);
        query.setFirstResult(0);
        query.setMaxResults(1);
        val list = query.getResultList();
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(list.get(0));
    }

    public Optional<UserAuthEntity> findByAuthToken(String authToken) {
        val manager = getEntityManager();
        // 通过手机号查询
        val sql = "SELECT u FROM UserAuthEntity u where u.authToken = :authToken";
        val query = manager.createQuery(sql, UserAuthEntity.class);
        query.setParameter("authToken", authToken);
        query.setFirstResult(0);
        query.setMaxResults(1);
        val list = query.getResultList();
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(list.get(0));
    }
}
