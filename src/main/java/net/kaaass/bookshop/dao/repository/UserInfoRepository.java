package net.kaaass.bookshop.dao.repository;

import lombok.val;
import net.kaaass.bookshop.dao.BaseRepository;
import net.kaaass.bookshop.dao.entity.UserAuthEntity;
import net.kaaass.bookshop.dao.entity.UserInfoEntity;

/**
 * 用户信息 DAO
 *
 * @author kaaass
 */
public class UserInfoRepository extends BaseRepository<UserInfoEntity, String> {

    public UserInfoEntity findByAuth(UserAuthEntity auth) {
        String sql = "select c from UserInfoEntity c where c.auth = ?1";
        return findOneBySql(sql, UserInfoEntity.class, auth).get();
    }

    public void deleteAllByAuth(UserAuthEntity auth) {
        val entity = findByAuth(auth);
        delete(entity);
    }
}
