package com.skills.validation;

import com.skills.validation.validator.OldPasswordCorrectValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OldPasswordCorrectValidator.class)
public @interface OldPasswordCorrect {
    String message() default "{com.ness.app.validation.OldPasswordCorrect.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
