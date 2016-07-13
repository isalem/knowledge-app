package com.ness.app.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.ness.app.domain.model.User;
import com.ness.app.validation.OldPasswordCorrect;
import com.ness.app.validation.PasswordConfirmed;

@OldPasswordCorrect(message = "{OldPasswordCorrect.PasswordSettingsForm}")
@PasswordConfirmed(message = "{PasswordConfirmed.PasswordSettingsForm}")
public class PasswordSettingsForm {
	private Long id;

	@NotNull
	@NotEmpty(message = "{NotEmpty.password}")
	@Length(min = 6, message = "{Length.password}")
	private String oldPassword;
	
	@NotNull
	@NotEmpty(message = "{NotEmpty.password}")
	@Length(min = 6, message = "{Length.password}")
	private String newPassword;
	
	@NotNull
	@NotEmpty(message = "{NotEmpty.password}")
	@Length(min = 6, message = "{Length.password}")
	private String confirmNewPassword;

	public PasswordSettingsForm() { }
	
	public PasswordSettingsForm(User user) {
		this.id = user.getUserId();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getOldPassword() {
		return oldPassword;
	}
	
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
}
