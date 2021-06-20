package bookshop.dao.repository;

import bookshop.dao.BaseRepository;
import bookshop.dao.entity.CategoryEntity;

import javax.ejb.Stateless;
import java.util.List;

/**
 * 分类 DAO
 *
 * @author kaaass
 */
@Stateless
public class CategoryRepository extends BaseRepository<CategoryEntity, String> {

    /**
     * 通过父分类查找
     */
    public List<CategoryEntity> findAllByParent(CategoryEntity parent) {
        String sql = "SELECT u FROM CategoryEntity u where u.parent = ?1";
        return findAllBySql(sql, CategoryEntity.class, parent);
    }
}
