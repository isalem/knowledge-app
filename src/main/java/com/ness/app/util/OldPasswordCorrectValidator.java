package com.ness.app.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ness.app.settings.PasswordSettingsForm;
import com.ness.app.user.User;
import com.ness.app.user.UserService;

public class OldPasswordCorrectValidator implements ConstraintValidator<OldPasswordCorrect, PasswordSettingsForm> {

	@Autowired
	private UserService userService;
	
	@Override
	public void initialize(OldPasswordCorrect constraintAnnotation) {
		// TODO Auto-generated method stub
		
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
