package com.ness.knowledges.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ness.knowledges.domain.User;
import com.ness.knowledges.persistent.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return "user/home";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String userPage(@PathVariable Long id, Model model) {
		User user = userRepository.findOne(id);
		model.addAttribute("user", user);
		return "user/user-page";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createNewUser(Model model) {
		model.addAttribute("newUser", new User());
		return "user/create";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(User newUser) {
		User user = userRepository.save(newUser);
		return "redirect:/user/" + user.getId();
	}
}
