package com.ness.app.util;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.ness.app.user.UserSearchForm;

public abstract class BaseController {
	
	@ModelAttribute("userSearchForm")
	public UserSearchForm searchForm() {
		return new UserSearchForm();
	}
}
