package net.kaaass.bookshop.constraints;

import net.kaaass.bookshop.exception.BadRequestException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsbnValidator implements ConstraintValidator<Isbn, String> {

    private Isbn isbn;

    @Override
    public void initialize(Isbn isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            format(s);
            return true;
        } catch (BadRequestException e) {
            return false;
        }
    }

    public static String format(String id) throws BadRequestException {
        if (id.length() != 13) {
            throw new BadRequestException("ISBN长度必须为13！");
        }
        for (char ch : id.toCharArray()) {
            if (!Character.isDigit(ch)) {
                throw new BadRequestException("ISBN必须为13位数字！");
            }
        }

        String prefix = id.substring(0, 3);
        if (!"978".equals(prefix)) {
            throw new BadRequestException("ISBN开头3位必须为978！");
        }

        String countryCode = id.substring(3, 4);
        String publisher = id.substring(4, 8);
        String pubNumber = id.substring(8, 12);
        String validate = id.substring(12, 13);

        int actual = 0;
        for (int i = 0; i < 12; i++) {
            char ch = id.charAt(i);
            if (i % 2 == 0) {
                actual += (ch - '0');
            } else {
                actual += (ch - '0') * 3;
            }
        }
        actual = (10 - (actual % 10)) % 10;
        int expected = id.charAt(12) - '0';
        if (actual != expected) {
            throw new BadRequestException(String.format("ISBN校验码错误，应为 %d 实际为 %d！", expected, actual));
        }

        return String.format("ISBN %s-%s-%s-%s-%s", prefix, countryCode, publisher, pubNumber, validate);
    }
}
