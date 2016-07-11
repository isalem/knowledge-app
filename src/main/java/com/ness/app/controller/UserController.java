package com.ness.app.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ness.app.domain.model.Knowledge;
import com.ness.app.domain.model.User;
import com.ness.app.service.UserService;
import com.ness.app.veiw.CreateUserForm;
import com.ness.app.veiw.UserSearchForm;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Principal principal, Model model) {
		return "redirect:/user/" + principal.getName();
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	@PreAuthorize("#username == principal.username or hasAnyRole('HR', 'BUSINESS')")
	public String userPage(@PathVariable String username, Model model, Principal principal, HttpSession session) {
		User user = userService.findUserByUsername(username);
		Set<Knowledge> knowledges = user.getKnowledges();
		Boolean isEmptyKnowledges = knowledges.isEmpty();
		
		Map<String, List<Knowledge>> knowledgesGropedByAreaTitle = knowledges.stream()
				.collect(Collectors.groupingBy(k -> k.getArea().getTitle()));
		
		model.addAttribute("isEmptyKnowledges", isEmptyKnowledges);
		model.addAttribute("knowledgesGropedByAreaTitle", knowledgesGropedByAreaTitle);
		model.addAttribute("user", user);
		return "user/user-page";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createNewUser(Model model) {
		model.addAttribute("createUserForm", new CreateUserForm());
		return "user/create";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(
			@Valid @ModelAttribute CreateUserForm createUserForm, 
			BindingResult result,
			Model model) {
		
		if (result.hasErrors()) {
			return "user/create";
		}
		
		User newUser = new User(createUserForm);
		newUser = userService.saveAndLogin(newUser);
		
		return "redirect:/user/" + newUser.getUsername();
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchUsers(@ModelAttribute("userSearchForm") UserSearchForm userSearchForm, Principal principal, Model model) {
		
		Set<User> usersWithRequestedKnowledges = userService.findUsersWithKnowledges(userSearchForm.getParsedSearchRequest());
		model.addAttribute("usersWithRequestedKnowledges", usersWithRequestedKnowledges);
		return "user/search";
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(UsernameNotFoundException.class)
	public String userNotFound() {
		return "user/error";
	}
}
