package com.ness.knowledges.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String test(Principal principal, Model model) {
		if (principal == null) {
			model.addAttribute("username", "Anonimous");
		} else {
			model.addAttribute("username", principal.getName());
		}
		return "home/home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "home/login";
	}
}
