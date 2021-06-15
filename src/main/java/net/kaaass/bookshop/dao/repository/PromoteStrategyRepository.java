package net.kaaass.bookshop.dao.repository;

import net.kaaass.bookshop.dao.BaseRepository;
import net.kaaass.bookshop.dao.entity.PromoteStrategyEntity;

import java.util.List;

/**
 * 打折策略 DAO
 * @author kaaass
 */
public class PromoteStrategyRepository extends BaseRepository<PromoteStrategyEntity, String> {

    public List<PromoteStrategyEntity> findAllByEnabledTrueOrderByOrderAscLastUpdateTimeDesc() {
        String sql = "SELECT u FROM PromoteStrategyEntity u where u.enabled = ?1 order by u.order asc, u.lastUpdateTime desc";
        return findAllBySql(sql, PromoteStrategyEntity.class, true);
    }
}
