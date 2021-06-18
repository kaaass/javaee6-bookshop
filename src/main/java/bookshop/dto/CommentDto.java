package bookshop.dto;

import bookshop.util.DateToLongSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * 评论 DTO
 *
 * @author kaaass
 */
public class CommentDto {

    private String id;

    private String uid;

    private String orderId;

    private String productId;

    private int rate;

    private String content;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date commentTime;

    public CommentDto() {
    }

    public String getId() {
        return this.id;
    }

    public String getUid() {
        return this.uid;
    }

    public String getOrderId() {
        return this.orderId;
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

    public Date getCommentTime() {
        return this.commentTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CommentDto)) return false;
        final CommentDto other = (CommentDto) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$uid = this.getUid();
        final Object other$uid = other.getUid();
        if (this$uid == null ? other$uid != null : !this$uid.equals(other$uid)) return false;
        final Object this$orderId = this.getOrderId();
        final Object other$orderId = other.getOrderId();
        if (this$orderId == null ? other$orderId != null : !this$orderId.equals(other$orderId)) return false;
        final Object this$productId = this.getProductId();
        final Object other$productId = other.getProductId();
        if (this$productId == null ? other$productId != null : !this$productId.equals(other$productId)) return false;
        if (this.getRate() != other.getRate()) return false;
        final Object this$content = this.getContent();
        final Object other$content = other.getContent();
        if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
        final Object this$commentTime = this.getCommentTime();
        final Object other$commentTime = other.getCommentTime();
        return this$commentTime == null ? other$commentTime == null : this$commentTime.equals(other$commentTime);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CommentDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $uid = this.getUid();
        result = result * PRIME + ($uid == null ? 43 : $uid.hashCode());
        final Object $orderId = this.getOrderId();
        result = result * PRIME + ($orderId == null ? 43 : $orderId.hashCode());
        final Object $productId = this.getProductId();
        result = result * PRIME + ($productId == null ? 43 : $productId.hashCode());
        result = result * PRIME + this.getRate();
        final Object $content = this.getContent();
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        final Object $commentTime = this.getCommentTime();
        result = result * PRIME + ($commentTime == null ? 43 : $commentTime.hashCode());
        return result;
    }

    public String toString() {
        return "CommentDto(id=" + this.getId() + ", uid=" + this.getUid() + ", orderId=" + this.getOrderId() + ", productId=" + this.getProductId() + ", rate=" + this.getRate() + ", content=" + this.getContent() + ", commentTime=" + this.getCommentTime() + ")";
    }
}
