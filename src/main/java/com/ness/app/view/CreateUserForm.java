package com.ness.app.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.ness.app.domain.model.UserRole;

public class CreateUserForm {
	
	@NotNull
	@NotEmpty
	@Length(max = 100)
	private String name;
	
	@NotNull
	@NotEmpty
	@Length(max = 100)
	private String email;
	
	@NotNull
	@NotEmpty
	@Length(max = 30)
	private String username;
	
	@NotNull
	@NotEmpty
	private String password;
	
	private UserRole authoritie;
	
	public CreateUserForm() { }
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getAuthoritie() {
		return authoritie;
	}

	public void setAuthoritie(UserRole authoritie) {
		this.authoritie = authoritie;
	}
}
