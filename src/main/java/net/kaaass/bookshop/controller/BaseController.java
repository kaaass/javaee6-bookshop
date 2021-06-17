package net.kaaass.bookshop.controller;

import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.security.SecurityIdentity;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 控制器基类
 * @author kaaass
 */
public abstract class BaseController {

    /**
     * 校验 Bean
     */
    public void validateBean(Validator validator, Object object) throws BadRequestException {
        System.out.println(validator);
        Set<ConstraintViolation<Object>> result = validator.validate(object);
        if (!result.isEmpty()) {
            StringBuilder reason = new StringBuilder();
            for (ConstraintViolation<Object> violation : result) {
                reason.append(violation.getMessage()).append("; ");
            }
            throw new BadRequestException(reason.toString());
        }
    }

    public String getUid(SecurityIdentity identity) {
        return identity.getUserAuthDto().getId();
    }
}
