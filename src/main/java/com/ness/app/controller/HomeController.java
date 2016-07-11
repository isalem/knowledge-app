package com.ness.app.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends BaseController {
	
	@RequestMapping("/")
	public String test(Principal principal, Model model) {
		return "home/home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "home/login";
	}
}