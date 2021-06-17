package net.kaaass.bookshop.security;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * 接口鉴权注解
 *
 * @author kaaass
 */
@Target({TYPE, METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Secured {

    SecurityRole value() default SecurityRole.LOGGED;
}
