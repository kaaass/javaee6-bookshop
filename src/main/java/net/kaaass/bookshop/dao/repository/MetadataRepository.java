package net.kaaass.bookshop.dao.repository;

import java8.util.Optional;
import net.kaaass.bookshop.dao.BaseRepository;
import net.kaaass.bookshop.dao.entity.MetadataEntity;

/**
 * 全局元数据
 * @author kaaass
 */
public class MetadataRepository extends BaseRepository<MetadataEntity, String> {

    /**
     * 查询元数据
     */
    public Optional<MetadataEntity> findByKey(String key) {
        String sql = "select c from MetadataEntity c where c.key = ?1";
        return findOneBySql(sql, MetadataEntity.class, key);
    }
}
