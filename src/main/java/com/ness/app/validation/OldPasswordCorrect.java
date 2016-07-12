package com.ness.app.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ness.app.validation.validator.OldPasswordCorrectValidator;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OldPasswordCorrectValidator.class)
public @interface OldPasswordCorrect {
	String message() default "{com.ness.app.util.OldPasswordCorrect.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
