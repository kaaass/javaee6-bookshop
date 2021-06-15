package net.kaaass.bookshop.dao.repository;

import java8.util.Optional;
import net.kaaass.bookshop.dao.BaseRepository;
import net.kaaass.bookshop.dao.Pageable;
import net.kaaass.bookshop.dao.entity.OrderEntity;
import net.kaaass.bookshop.dto.OrderType;

import java.sql.Timestamp;
import java.util.List;

/**
 * 订单 DAO
 * @author kaaass
 */
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
        return findOneBySql(sql, Integer.class, uid, type);
    }

    public List<OrderEntity> findAllByTypeOrderByCreateTimeDesc(OrderType type, Pageable page) {
        String sql = "SELECT u FROM OrderEntity u where u.type = ?1 order by u.createTime desc";
        return findAllBySql(sql, page, OrderEntity.class, type);
    }

    public List<OrderEntity> findAllByUidAndTypeOrderByCreateTimeDesc(String uid, OrderType type, Pageable page) {
        String sql = "SELECT u FROM OrderEntity u where u.uid = ?1 and u.type = ?2 order by u.createTime desc";
        return findAllBySql(sql, page, OrderEntity.class, uid, type);
    }
}
