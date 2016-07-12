package com.ness.app.helper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ness.app.domain.model.User;
import com.ness.app.service.UserService;
import com.ness.app.view.PasswordSettingsForm;

public class OldPasswordCorrectValidator implements ConstraintValidator<OldPasswordCorrect, PasswordSettingsForm> {

	@Autowired
	private UserService userService;
	
	@Override
	public void initialize(OldPasswordCorrect constraintAnnotation) {
		//Nothing to do here
	}

	@Override
	public boolean isValid(PasswordSettingsForm form, ConstraintValidatorContext context) {

		User user = userService.findUserById(form.getId());
		
		if (form.getOldPassword().equals(user.getPassword())) {
			return true;
		} else {
			return false;
		}
	}
}
