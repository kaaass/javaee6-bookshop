package bookshop.dao.repository;

import java8.util.Optional;
import java8.util.function.Function;
import bookshop.dao.BaseRepository;
import bookshop.dao.Pageable;
import bookshop.dao.entity.OrderEntity;
import bookshop.dao.entity.ProductEntity;
import bookshop.dto.OrderType;

import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.util.List;

/**
 * 订单 DAO
 *
 * @author kaaass
 */
@Stateless
public class OrderRepository extends BaseRepository<OrderEntity, String> {

    public boolean existsByRequestId(String requestId) {
        String sql = "select 1 from OrderEntity where exist(select c from OrderEntity c where c.requestId = ?1)";
        return findOneBySql(sql, Integer.class, requestId).isPresent();
    }

    public Optional<OrderEntity> findByRequestId(String requestId) {
        String sql = "select c from OrderEntity c where c.requestId = ?1";
        return findOneBySql(sql, OrderEntity.class, requestId);
    }

    public List<OrderEntity> findAllByTypeIsNotOrderByCreateTimeDesc(OrderType type, Pageable page) {
        String sql = "SELECT u FROM OrderEntity u where u.type != ?1 order by u.createTime desc";
        return findAllBySql(sql, page, OrderEntity.class, type);
    }

    public List<OrderEntity> findAllByUidAndTypeIsNotOrderByCreateTimeDesc(String uid, OrderType type, Pageable page) {
        String sql = "SELECT u FROM OrderEntity u where u.uid = ?1 and u.type != ?2 order by u.createTime desc";
        return findAllBySql(sql, page, OrderEntity.class, uid, type);
    }

    public Optional<OrderEntity> findFirstByCreateTimeBetweenOrderByCreateTimeDesc(Timestamp start, Timestamp end) {
        String sql = "select c from OrderEntity c where c.createTime between ?1 and ?2 order by c.createTime desc";
        return findOneBySql(sql, OrderEntity.class, start, end);
    }

    public Optional<Integer> countAllByUidAndType(String uid, OrderType type) {
        String sql = "select count(c.id) from OrderEntity c where c.uid = ?1 and c.type = ?2";
        return findOneBySql(sql, Long.class, uid, type)
                .map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(Long aLong) {
                        return aLong.intValue();
                    }
                });
    }

    public List<OrderEntity> findAllByTypeOrderByCreateTimeDesc(OrderType type, Pageable page) {
        String sql = "SELECT u FROM OrderEntity u where u.type = ?1 order by u.createTime desc";
        return findAllBySql(sql, page, OrderEntity.class, type);
    }

    public List<OrderEntity> findAllByUidAndTypeOrderByCreateTimeDesc(String uid, OrderType type, Pageable page) {
        String sql = "SELECT u FROM OrderEntity u where u.uid = ?1 and u.type = ?2 order by u.createTime desc";
        return findAllBySql(sql, page, OrderEntity.class, uid, type);
    }

    public List<OrderEntity> findAllByProduct(ProductEntity productEntity, Pageable page) {
        String sql = "SELECT distinct(c) FROM OrderItemEntity u JOIN u.order c where u.product = ?1 order by c.createTime desc";
        return findAllBySql(sql, page, OrderEntity.class, productEntity);
    }
}
