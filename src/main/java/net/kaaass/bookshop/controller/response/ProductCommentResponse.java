package net.kaaass.bookshop.controller.response;

import net.kaaass.bookshop.vo.CommentVo;

import java.util.List;

/**
 * 物品评论请求返回
 * @author kaaass
 */
public class ProductCommentResponse {

    private Float averageRate;

    private List<CommentVo> comments;

    public ProductCommentResponse() {
    }

    public Float getAverageRate() {
        return this.averageRate;
    }

    public List<CommentVo> getComments() {
        return this.comments;
    }

    public void setAverageRate(Float averageRate) {
        this.averageRate = averageRate;
    }

    public void setComments(List<CommentVo> comments) {
        this.comments = comments;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ProductCommentResponse)) return false;
        final ProductCommentResponse other = (ProductCommentResponse) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$averageRate = this.getAverageRate();
        final Object other$averageRate = other.getAverageRate();
        if (this$averageRate == null ? other$averageRate != null : !this$averageRate.equals(other$averageRate))
            return false;
        final Object this$comments = this.getComments();
        final Object other$comments = other.getComments();
        if (this$comments == null ? other$comments != null : !this$comments.equals(other$comments)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ProductCommentResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $averageRate = this.getAverageRate();
        result = result * PRIME + ($averageRate == null ? 43 : $averageRate.hashCode());
        final Object $comments = this.getComments();
        result = result * PRIME + ($comments == null ? 43 : $comments.hashCode());
        return result;
    }

    public String toString() {
        return "ProductCommentResponse(averageRate=" + this.getAverageRate() + ", comments=" + this.getComments() + ")";
    }
}
