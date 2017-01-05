package com.skills.validation.validator;

import com.skills.domain.model.User;
import com.skills.service.UserService;
import com.skills.validation.OldPasswordCorrect;
import com.skills.view.PasswordSettingsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
        // Nothing to do here
    }

    @Override
    public boolean isValid(PasswordSettingsForm form, ConstraintValidatorContext context) {
        User user = userService.findUserById(form.getId());
        return passwordEncoder.matches(form.getOldPassword(), user.getPassword());
    }
}
