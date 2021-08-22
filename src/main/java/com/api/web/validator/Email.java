package com.api.web.validator;

import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.springframework.util.StringUtils.hasText;

@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = Email.Validator.class)
public @interface Email {

    String message() default "Invalid email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Component
    class Validator implements ConstraintValidator<Email, String> {

        private static final String REGEXP = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        public static String getEmailRegExp() {
            return REGEXP;
        }

        @Override
        public void initialize(Email annotation) {

        }

        @Override
        public boolean isValid(final String email, final ConstraintValidatorContext context) {
            if (!hasText(email)) {
                return true;
            }

            return Pattern.compile(REGEXP).matcher(email).matches();
        }
    }
}