package bookshop.dao.repository;

import bookshop.dao.BaseRepository;
import bookshop.dao.Pageable;
import bookshop.dao.entity.MediaEntity;

import java.util.List;

/**
 * 媒体资源 DAO
 *
 * @author kaaass
 */
public class MediaRepository extends BaseRepository<MediaEntity, String> {

    /**
     * 按上传时间倒序
     */
    public List<MediaEntity> findAllByOrderByUploadTimeDesc(Pageable page) {
        String sql = "SELECT u FROM MediaEntity u order by u.uploadTime desc";
        return findAllBySql(sql, page, MediaEntity.class);
    }
}
