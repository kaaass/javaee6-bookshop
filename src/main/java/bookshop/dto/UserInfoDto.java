package bookshop.dto;

/**
 * 用户信息 DTO
 *
 * @author kaaass
 */
public class UserInfoDto {

    private UserAuthDto auth;

    private String wechat;

    private MediaDto avatar;

    public UserInfoDto() {
    }

    public UserAuthDto getAuth() {
        return this.auth;
    }

    public String getWechat() {
        return this.wechat;
    }

    public MediaDto getAvatar() {
        return this.avatar;
    }

    public void setAuth(UserAuthDto auth) {
        this.auth = auth;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public void setAvatar(MediaDto avatar) {
        this.avatar = avatar;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserInfoDto)) return false;
        final UserInfoDto other = (UserInfoDto) o;
        if (!other.canEqual(this)) return false;
        final Object this$auth = this.getAuth();
        final Object other$auth = other.getAuth();
        if (this$auth == null ? other$auth != null : !this$auth.equals(other$auth)) return false;
        final Object this$wechat = this.getWechat();
        final Object other$wechat = other.getWechat();
        if (this$wechat == null ? other$wechat != null : !this$wechat.equals(other$wechat)) return false;
        final Object this$avatar = this.getAvatar();
        final Object other$avatar = other.getAvatar();
        return this$avatar == null ? other$avatar == null : this$avatar.equals(other$avatar);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserInfoDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $auth = this.getAuth();
        result = result * PRIME + ($auth == null ? 43 : $auth.hashCode());
        final Object $wechat = this.getWechat();
        result = result * PRIME + ($wechat == null ? 43 : $wechat.hashCode());
        final Object $avatar = this.getAvatar();
        result = result * PRIME + ($avatar == null ? 43 : $avatar.hashCode());
        return result;
    }

    public String toString() {
        return "UserInfoDto(auth=" + this.getAuth() + ", wechat=" + this.getWechat() + ", avatar=" + this.getAvatar() + ")";
    }
}
