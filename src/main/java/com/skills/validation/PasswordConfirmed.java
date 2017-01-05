package com.skills.validation;

import com.skills.validation.validator.PasswordConfirmedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConfirmedValidator.class)
public @interface PasswordConfirmed {
    String message() default "{com.ness.app.validation.PasswordConfirmed.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
