package net.kaaass.bookshop.controller.request;

import lombok.Data;
import net.kaaass.bookshop.constraints.Uuid;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 评论请求
 */
@Data
public class CommentRequest {

    @Data
    public static class Content {

        @Uuid
        private String productId;

        @Min(value = 0, message = "评分最小为0")
        @Max(value = 5, message = "评分最大为5")
        private int rate;

        @Size(
                message = "评论最少5字，最长200字",
                min = 5,
                max = 200
        )
        private String content;
    }

    private List<Content> comments;
}
