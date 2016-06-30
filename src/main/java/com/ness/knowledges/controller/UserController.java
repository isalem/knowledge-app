package com.ness.knowledges.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ness.knowledges.domain.Knowledge;
import com.ness.knowledges.domain.User;
import com.ness.knowledges.service.interfaces.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return "user/home";
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String userPage(@PathVariable String username, Model model) {
		User user = userService.findUserByUsername(username);
		Set<Knowledge> knowledges = user.getKnowledges();
		Boolean isEmptyKnowledges = knowledges.isEmpty();
		
		Map<String, List<Knowledge>> knowledgesGropedByAreaTitle = knowledges.stream()
				.collect(Collectors.groupingBy(k -> k.getArea().getTitle()));
		
		model.addAttribute("isEmptyKnowledges", isEmptyKnowledges);
		model.addAttribute("knowledgesGropedByAreaTitle", knowledgesGropedByAreaTitle);
		model.addAttribute("user", user);
		model.addAttribute("username", username);
		return "user/user-page";
	}
//	
//	@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public String createNewUser(Model model) {
//		model.addAttribute("user", new User());
//		return "user/create";
//	}
//	
//	@RequestMapping(value = "/create", method = RequestMethod.POST)
//	public String create(@Valid User user, BindingResult error, Model model) {
//		if (error.hasErrors()) {
//			return "user/create";
//		}
//		model.addAttribute("user", user);
//		userService.saveUser(user);
//		return "redirect:/user/" + user.getUsername();
//	}
	
	@RequestMapping(value = "/edit/{username}", method = RequestMethod.GET)
	public String edit(@PathVariable String username, Principal principal, Model model) {
		if (principal.getName().equals(username)) {
			User user = userService.findUserByUsername(username);
			model.addAttribute("user", user);
			return "user/edit";
		} else {
			throw new AccessDeniedException("Access denied");
		}
		
	}
	
	@RequestMapping(value = "/edit/{username}", method = RequestMethod.POST)
	public String edit(@PathVariable String username, @Valid User user, BindingResult error, Model model) {
		if (error.hasErrors()) {
			return "user/edit";
		}
		model.addAttribute("user", user);
		userService.updateUserByUsername(username, user);
		
		return "redirect:/user/" + user.getUsername();
	}
}
