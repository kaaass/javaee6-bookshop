package net.kaaass.bookshop.dao.repository;

import java8.util.Optional;
import net.kaaass.bookshop.dao.BaseRepository;
import net.kaaass.bookshop.dao.entity.PluginEntity;

import java.util.List;

/**
 * 插件 DAO
 * @author kaaass
 */
public class PluginRepository extends BaseRepository<PluginEntity, String> {

    public Optional<PluginEntity> findFirstByFilename(String filename) {
        String sql = "select c from PluginEntity c where c.filename = ?0";
        return findOneBySql(sql, PluginEntity.class, filename);
    }

    public List<PluginEntity> findAllByEnableTrue() {
        String sql = "SELECT u FROM PluginEntity u where u.enable = true";
        return findAllBySql(sql, PluginEntity.class);
    }
}
