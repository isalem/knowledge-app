package com.ness.app.settings;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.ness.app.user.User;
import com.ness.app.util.OldPasswordCorrect;

@OldPasswordCorrect
public class PasswordSettingsForm {
	private Long id;

	@NotNull
	@NotEmpty
	private String oldPassword;
	
	@NotNull
	@NotEmpty
	private String newPassword;
	
	public PasswordSettingsForm() {
		this.id = null;
		this.oldPassword = null;
		this.newPassword = null;
	}
	
	public PasswordSettingsForm(User user) {
		this.id = user.getUserId();
		this.oldPassword = null;
		this.newPassword = null;
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
}
