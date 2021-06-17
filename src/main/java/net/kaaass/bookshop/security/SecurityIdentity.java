package net.kaaass.bookshop.security;

import net.kaaass.bookshop.dto.UserAuthDto;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * 鉴权身份
 *
 * @author kaaass
 */
@SessionScoped
@Named("identity")
public class SecurityIdentity implements Serializable {

    private UserAuthDto userAuthDto;

    public SecurityIdentity() {
    }

    /**
     * 是否鉴权
     */
    public boolean isAuthorized() {
        return getUserAuthDto() != null;
    }

    /**
     * 获得当前权限
     */
    public SecurityRole getSecurityRole() {
        if (getUserAuthDto() == null) {
            return SecurityRole.ANONYMOUS;
        }
        return getUserAuthDto().getRole();
    }

    /**
     * 当前权限是否允许
     */
    public boolean canPermit(SecurityRole role) {
        return getSecurityRole().canPermitRole(role);
    }

    public UserAuthDto getUserAuthDto() {
        return this.userAuthDto;
    }

    public void setUserAuthDto(UserAuthDto userAuthDto) {
        this.userAuthDto = userAuthDto;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SecurityIdentity)) return false;
        final SecurityIdentity other = (SecurityIdentity) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$userAuthDto = this.getUserAuthDto();
        final Object other$userAuthDto = other.getUserAuthDto();
        if (this$userAuthDto == null ? other$userAuthDto != null : !this$userAuthDto.equals(other$userAuthDto))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SecurityIdentity;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userAuthDto = this.getUserAuthDto();
        result = result * PRIME + ($userAuthDto == null ? 43 : $userAuthDto.hashCode());
        return result;
    }

    public String toString() {
        return "SecurityIdentity(userAuthDto=" + this.getUserAuthDto() + ")";
    }
}
