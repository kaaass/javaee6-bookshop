package net.kaaass.bookshop.controller.request;

import lombok.Data;

import java.util.List;

/**
 * 评论请求
 */
@Data
public class CommentRequest {

    @Data
    public static class Content {
        private String productId;

        private int rate;

        private String content;
    }

    private List<Content> comments;
}
