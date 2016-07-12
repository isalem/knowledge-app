package com.ness.app.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.ness.app.view.UserSearchForm;

public abstract class BaseController {
	
	@ModelAttribute("userSearchForm")
	public UserSearchForm searchForm() {
		return new UserSearchForm();
	}
}
