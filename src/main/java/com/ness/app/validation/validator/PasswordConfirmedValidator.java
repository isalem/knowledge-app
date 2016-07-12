package com.ness.app.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ness.app.validation.PasswordConfirmed;
import com.ness.app.view.PasswordSettingsForm;

public class PasswordConfirmedValidator implements ConstraintValidator<PasswordConfirmed, PasswordSettingsForm> {

	@Override
	public void initialize(PasswordConfirmed constraintAnnotation) {
		// Nothing to do here
	}

	@Override
	public boolean isValid(PasswordSettingsForm form, ConstraintValidatorContext context) {
		return form.getNewPassword().equals(form.getConfirmNewPassword());
	}

}
