package net.kaaass.bookshop.dao.repository;

import net.kaaass.bookshop.dao.BaseRepository;
import net.kaaass.bookshop.dao.entity.CategoryEntity;

import java.util.List;

/**
 * 分类 DAO
 *
 * @author kaaass
 */
public class CategoryRepository extends BaseRepository<CategoryEntity, String> {

    /**
     * 通过父分类查找
     */
    public List<CategoryEntity> findAllByParent(CategoryEntity parent) {
        String sql = "SELECT u FROM CategoryEntity u where u.parent = ?1";
        return findAllBySql(sql, CategoryEntity.class, parent);
    }
}
