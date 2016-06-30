package com.ness.knowledges.controller;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.thymeleaf.standard.expression.AndExpression;

import com.ness.knowledges.domain.User;
import com.ness.knowledges.service.interfaces.UserService;

public class UserControllerTests {

	@Mock
	private UserService userService;
	
	@InjectMocks
	private UserController userController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
	
	@Test
	public void getHomePageTest() throws Exception {
		mockMvc.perform(get("/user/"))
			.andExpect(status().isOk())
			.andExpect(view().name("user/home"));
	}
	
	@Test
	public void getuUserPageTest() throws Exception {
		User testUser = new User("Foo Bar", "foo.bar@foo.com", "foo.bar", "foo.bar");
		when(userService.loadUserByUsername(isA(String.class))).thenReturn(testUser);
		
		mockMvc.perform(get("/user/foo.bar/"))
			.andExpect(status().isOk())
			.andExpect(view().name("user/user-page"))
			.andExpect(model().hasNoErrors())
			.andExpect(model().attributeExists("user"))
			.andExpect(model().attribute("user", equalTo(testUser)));
	}
	
	@Test
	public void postNewNotValidUserTest() throws Exception {
		mockMvc.perform(post("/user/create")
					.param("name", "Foo bar")
					.param("email", "foo.bar@foo.com")
					.param("password", "foo.bar"))
			.andExpect(model().hasErrors());
			
	}
	
	@Test
	public void postNewUserTest() throws Exception {
		mockMvc.perform(post("/user/create")
					.param("name", "Foo bar")
					.param("email", "foo.bar@foo.com")
					.param("username", "foo.bar")
					.param("password", "foo.bar")
					.param("authoritie", "ROLE_EMPLOY"))
			.andExpect(status().is3xxRedirection());
			
	}
}
