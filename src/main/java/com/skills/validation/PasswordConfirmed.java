package com.skills.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.skills.validation.validator.PasswordConfirmedValidator;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConfirmedValidator.class)
public @interface PasswordConfirmed {
	String message() default "{com.ness.app.validation.PasswordConfirmed.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
