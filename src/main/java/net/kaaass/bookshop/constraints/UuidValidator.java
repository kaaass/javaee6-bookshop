package net.kaaass.bookshop.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UuidValidator implements ConstraintValidator<Uuid, String> {

    private Uuid uuid;

    @Override
    public void initialize(Uuid uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if (s == null) {
            return uuid.nullable();
        }

        if (s.length() != 32) {
            return false;
        }

        for (char ch : s.toCharArray()) {
            boolean digit = '0' <= ch && ch <= '9';
            boolean alphabet = 'a' <= ch && ch <= 'z';
            if (!(digit || alphabet)) {
                return false;
            }
        }

        return true;
    }
}
