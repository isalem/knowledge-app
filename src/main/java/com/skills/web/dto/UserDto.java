package com.skills.web.dto;

import com.skills.domain.model.User;

public class UserDto {
	private Long id;
	private String name;
	private String username;
	private String email;
	private String authoritie;
	
	public UserDto() { }
	
	public UserDto(User user) {
		this.id = user.getUserId();
		this.name = user.getName();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.authoritie = user.getAuthoritie().getDescription();
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

	public String getAuthoritie() {
		return authoritie;
	}

	public void setAuthoritie(String authoritie) {
		this.authoritie = authoritie;
	}
}
