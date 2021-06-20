package bookshop.dao.repository;

import java8.util.Optional;
import bookshop.dao.BaseRepository;
import bookshop.dao.Pageable;
import bookshop.dao.entity.CommentEntity;

import javax.ejb.Stateless;
import java.util.List;

/**
 * 评论 DAO
 */
@Stateless
public class CommentRepository extends BaseRepository<CommentEntity, String> {

    /**
     * 按评论时间倒序查所有评论
     */
    public List<CommentEntity> findAllByOrderByCommentTimeDesc(Pageable page) {
        String sql = "SELECT u FROM CommentEntity u order by u.commentTime desc";
        return findAllBySql(sql, page, CommentEntity.class);
    }

    /**
     * 查找商品评论
     */
    public List<CommentEntity> findAllByProductIdOrderByRateDescCommentTimeDesc(String productId, Pageable page) {
        String sql = "SELECT u FROM CommentEntity u where u.productId = ?1 order by u.rate desc, u.commentTime desc";
        return findAllBySql(sql, page, CommentEntity.class, productId);
    }

    /**
     * 商品平均评分
     */
    public Optional<Double> averageRateByProductId(String productId) {
        String sql = "select avg(c.rate) from CommentEntity c where c.productId = ?1";
        return findOneBySql(sql, Double.class, productId);
    }
}
