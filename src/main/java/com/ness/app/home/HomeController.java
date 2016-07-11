package com.ness.app.home;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ness.app.util.BaseController;

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
