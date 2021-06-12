package net.kaaass.bookshop.security;

import lombok.Data;
import net.kaaass.bookshop.dto.UserAuthDto;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * 鉴权身份
 *
 * @author kaaass
 */
@Data
@SessionScoped
@Named("identity")
public class SecurityIdentity implements Serializable {

    private UserAuthDto userAuthDto;

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
}
