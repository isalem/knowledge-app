package com.ness.app.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ness.app.domain.model.User;
import com.ness.app.service.UserService;
import com.ness.app.validation.OldPasswordCorrect;
import com.ness.app.view.PasswordSettingsForm;

public class OldPasswordCorrectValidator implements ConstraintValidator<OldPasswordCorrect, PasswordSettingsForm> {

	private UserService userService;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public OldPasswordCorrectValidator(UserService userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void initialize(OldPasswordCorrect constraintAnnotation) {
		//Nothing to do here
	}

	@Override
	public boolean isValid(PasswordSettingsForm form, ConstraintValidatorContext context) {
		User user = userService.findUserById(form.getId());
		return passwordEncoder.matches(form.getOldPassword(), user.getPassword());
	}
}
