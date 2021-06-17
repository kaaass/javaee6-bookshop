package net.kaaass.bookshop.vo;

import net.kaaass.bookshop.util.DateToLongSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

public class CommentVo {

    private String id;

    private String avatar;

    private int rate;

    private String content;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date commentTime;

    public CommentVo() {
    }

    public String getId() {
        return this.id;
    }

    public String getAvatar() {
        return this.avatar;
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

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
        if (!(o instanceof CommentVo)) return false;
        final CommentVo other = (CommentVo) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$avatar = this.getAvatar();
        final Object other$avatar = other.getAvatar();
        if (this$avatar == null ? other$avatar != null : !this$avatar.equals(other$avatar)) return false;
        if (this.getRate() != other.getRate()) return false;
        final Object this$content = this.getContent();
        final Object other$content = other.getContent();
        if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
        final Object this$commentTime = this.getCommentTime();
        final Object other$commentTime = other.getCommentTime();
        return this$commentTime == null ? other$commentTime == null : this$commentTime.equals(other$commentTime);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CommentVo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $avatar = this.getAvatar();
        result = result * PRIME + ($avatar == null ? 43 : $avatar.hashCode());
        result = result * PRIME + this.getRate();
        final Object $content = this.getContent();
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        final Object $commentTime = this.getCommentTime();
        result = result * PRIME + ($commentTime == null ? 43 : $commentTime.hashCode());
        return result;
    }

    public String toString() {
        return "CommentVo(id=" + this.getId() + ", avatar=" + this.getAvatar() + ", rate=" + this.getRate() + ", content=" + this.getContent() + ", commentTime=" + this.getCommentTime() + ")";
    }
}
