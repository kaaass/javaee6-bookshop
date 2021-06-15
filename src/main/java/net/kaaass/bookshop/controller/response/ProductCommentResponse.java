package net.kaaass.bookshop.controller.response;

import lombok.Data;
import net.kaaass.bookshop.vo.CommentVo;

import java.util.List;

/**
 * 物品评论请求返回
 * @author kaaass
 */
@Data
public class ProductCommentResponse {

    private Float averageRate;

    private List<CommentVo> comments;
}
