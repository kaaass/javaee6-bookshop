package net.kaaass.bookshop.controller.request;

import net.kaaass.bookshop.constraints.Uuid;

import javax.validation.constraints.Size;

public class UserInfoModifyRequest {

    @Size(
            message = "wechat格式错误",
            min = 1,
            max = 50
    )
    private String wechat;

    @Uuid(message = "avatar格式错误")
    private String avatar;

    public UserInfoModifyRequest() {
    }

    public String getWechat() {
        return this.wechat;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserInfoModifyRequest)) return false;
        final UserInfoModifyRequest other = (UserInfoModifyRequest) o;
        if (!other.canEqual(this)) return false;
        final Object this$wechat = this.getWechat();
        final Object other$wechat = other.getWechat();
        if (this$wechat == null ? other$wechat != null : !this$wechat.equals(other$wechat)) return false;
        final Object this$avatar = this.getAvatar();
        final Object other$avatar = other.getAvatar();
        return this$avatar == null ? other$avatar == null : this$avatar.equals(other$avatar);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserInfoModifyRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $wechat = this.getWechat();
        result = result * PRIME + ($wechat == null ? 43 : $wechat.hashCode());
        final Object $avatar = this.getAvatar();
        result = result * PRIME + ($avatar == null ? 43 : $avatar.hashCode());
        return result;
    }

    public String toString() {
        return "UserInfoModifyRequest(wechat=" + this.getWechat() + ", avatar=" + this.getAvatar() + ")";
    }
}
