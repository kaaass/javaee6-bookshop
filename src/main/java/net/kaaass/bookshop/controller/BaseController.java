package net.kaaass.bookshop.controller;

import lombok.val;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.security.SecurityIdentity;

import javax.validation.Validator;

/**
 * 控制器基类
 *
 * @author kaaass
 */
public abstract class BaseController {

    /**
     * 校验 Bean
     */
    public void validateBean(Validator validator, Object object) throws BadRequestException {
        System.out.println(validator);
        val result = validator.validate(object);
        if (!result.isEmpty()) {
            StringBuilder reason = new StringBuilder();
            for (val violation : result) {
                reason.append(violation.getMessage()).append("; ");
            }
            throw new BadRequestException(reason.toString());
        }
    }

    public String getUid(SecurityIdentity identity) {
        return identity.getUserAuthDto().getId();
    }
}
