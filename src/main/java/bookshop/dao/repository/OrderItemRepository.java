package bookshop.dao.repository;

import java8.util.Optional;
import bookshop.dao.BaseRepository;
import bookshop.dao.entity.OrderItemEntity;
import bookshop.dao.entity.ProductEntity;

import javax.ejb.Stateless;
import java.sql.Timestamp;

/**
 * 订单项目 DAO
 *
 * @author kaaass
 */
@Stateless
public class OrderItemRepository extends BaseRepository<OrderItemEntity, String> {

    /**
     * 统计销售数量
     */
    public Optional<Long> sumCountByIdBetween(ProductEntity productEntity, Timestamp st, Timestamp ed) {
        String sql = "select sum(e.count) from OrderItemEntity e where e.product = ?1 and e.createTime >= ?2 and e.createTime < ?3";
        return findOneBySql(sql, Long.class, productEntity, st, ed);
    }
}
