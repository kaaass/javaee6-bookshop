package bookshop.security;

import java8.util.function.Predicate;
import java8.util.stream.StreamSupport;

import java.util.Arrays;

/**
 * 权限等级
 *
 * @author kaaass
 */
public enum SecurityRole {

    /**
     * 管理员
     */
    ADMIN(90),

    /**
     * 普通用户
     */
    USER(20),

    /**
     * 已登录
     */
    LOGGED(5),

    /**
     * 匿名
     */
    ANONYMOUS(0);

    private final int level;

    SecurityRole(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public static SecurityRole ofLevel(final int level) {
        return StreamSupport.stream(Arrays.asList(values()))
                .filter(new Predicate<SecurityRole>() {
                    @Override
                    public boolean test(SecurityRole cur) {
                        return cur.level == level;
                    }
                })
                .findAny().orElse(ANONYMOUS);
    }

    /**
     * 是否能承担职责
     */
    public boolean canPermitRole(SecurityRole role) {
        return this.level >= role.level;
    }

    /**
     * 权限是否比 role 更低
     */
    public boolean lowThan(SecurityRole role) {
        return this.level < role.level;
    }
}
