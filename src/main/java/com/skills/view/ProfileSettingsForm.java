package com.skills.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.skills.domain.model.User;

public class ProfileSettingsForm {
	
	private Long id;
	
	@NotNull
	@NotEmpty(message = "{NotEmpty.name}")
	@Length(max = 100, message = "{Length.name}")
	private String name;
	
	@NotNull
	@NotEmpty(message = "{NotEmpty.username}")
	@Length(max = 100, message = "{Length.username}")
	private String username;
	
	@NotNull
	@NotEmpty(message = "{NotEmpty.email}")
	@Email(message = "{Email.email}")
	@Length(max = 100, message = "{Length.email}")
	private String email;
	
	public ProfileSettingsForm() {
		this.id = null;
		this.name = null;
		this.username = null;
		this.email = null;
	}
	
	public ProfileSettingsForm(User user) {
		this.id = user.getUserId();
		this.name = user.getName();
		this.username = user.getUsername();
		this.email = user.getEmail();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
