package net.kaaass.bookshop.controller.request;

import net.kaaass.bookshop.constraints.Uuid;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 评论请求
 */
public class CommentRequest {

    public CommentRequest() {
    }

    public List<Content> getComments() {
        return this.comments;
    }

    public void setComments(List<Content> comments) {
        this.comments = comments;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CommentRequest)) return false;
        final CommentRequest other = (CommentRequest) o;
        if (!other.canEqual(this)) return false;
        final Object this$comments = this.getComments();
        final Object other$comments = other.getComments();
        return this$comments == null ? other$comments == null : this$comments.equals(other$comments);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CommentRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $comments = this.getComments();
        result = result * PRIME + ($comments == null ? 43 : $comments.hashCode());
        return result;
    }

    public String toString() {
        return "CommentRequest(comments=" + this.getComments() + ")";
    }

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

        public Content() {
        }

        public String getProductId() {
            return this.productId;
        }

        public int getRate() {
            return this.rate;
        }

        public String getContent() {
            return this.content;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Content)) return false;
            final Content other = (Content) o;
            if (!other.canEqual(this)) return false;
            final Object this$productId = this.getProductId();
            final Object other$productId = other.getProductId();
            if (this$productId == null ? other$productId != null : !this$productId.equals(other$productId))
                return false;
            if (this.getRate() != other.getRate()) return false;
            final Object this$content = this.getContent();
            final Object other$content = other.getContent();
            return this$content == null ? other$content == null : this$content.equals(other$content);
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Content;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $productId = this.getProductId();
            result = result * PRIME + ($productId == null ? 43 : $productId.hashCode());
            result = result * PRIME + this.getRate();
            final Object $content = this.getContent();
            result = result * PRIME + ($content == null ? 43 : $content.hashCode());
            return result;
        }

        public String toString() {
            return "CommentRequest.Content(productId=" + this.getProductId() + ", rate=" + this.getRate() + ", content=" + this.getContent() + ")";
        }
    }

    private List<Content> comments;
}
