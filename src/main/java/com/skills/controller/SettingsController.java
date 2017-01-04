package com.skills.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skills.domain.model.Skill;
import com.skills.domain.model.User;
import com.skills.domain.wrapper.SkillWithSelection;
import com.skills.service.SkillService;
import com.skills.service.UserService;
import com.skills.view.PasswordSettingsForm;
import com.skills.view.ProfileSettingsForm;
import com.skills.view.SkillSettingsForm;

@Controller
@RequestMapping("/settings")
public class SettingsController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SkillService skillService;
	
	@RequestMapping(value = { "", "/", "/profile" }, method = RequestMethod.GET)
	@PreAuthorize("#editableUser == principal.username or hasRole('HR')")
	public String showProfileSettings(
			@RequestParam("editableUser") String editableUser, 
			@RequestParam(name = "userUpdated", required = false, defaultValue = "false") Boolean userUpdated, 
			Model model) {
		
		model.addAttribute("activePage", "profile");
		model.addAttribute("editableUser", editableUser);
		model.addAttribute("userUpdated", userUpdated);
		
		User user = userService.findUserByUsername(editableUser);
		model.addAttribute("profileSettingsForm", new ProfileSettingsForm(user));
		
		return "settings/profile";
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	@PreAuthorize("#editableUser == principal.username or hasRole('HR')")
	public String updateProfileSettings(
			@RequestParam("editableUser") String editableUser,
			@Valid @ModelAttribute ProfileSettingsForm profileSettingsForm, 
			BindingResult result,
			Model model) {
		
		model.addAttribute("editableUser", profileSettingsForm.getUsername());
		
		if (result.hasErrors()) {
			model.addAttribute("activePage", "profile");
			model.addAttribute("editableUser", editableUser);
			return "settings/profile";
		}
		
		userService.updateUserProfile(profileSettingsForm);
		
		model.addAttribute("userUpdated", true);
		
		return "redirect:/settings/profile";
	}
	
	@RequestMapping(value = "/password", method = RequestMethod.GET)
	@PreAuthorize("#editableUser == principal.username or hasRole('HR')")
	public String showPasswordSettings(
			@RequestParam("editableUser") String editableUser, 
			@RequestParam(name = "userUpdated", required = false, defaultValue = "false") Boolean userUpdated, 
			Model model) {
		
		model.addAttribute("activePage", "password");
		model.addAttribute("editableUser", editableUser);
		model.addAttribute("userUpdated", userUpdated);
		
		User user = userService.findUserByUsername(editableUser);
		model.addAttribute("passwordSettingsForm", new PasswordSettingsForm(user));
		
		return "settings/password";
	}
	
	@RequestMapping(value = "/password", method = RequestMethod.POST)
	@PreAuthorize("#editableUser == principal.username or hasRole('HR')")
	public String updatePasswordSettings(
			@RequestParam("editableUser") String editableUser,
			@Valid @ModelAttribute PasswordSettingsForm passwordSettingsForm,
			BindingResult result,
			Model model) {
		
		model.addAttribute("editableUser", editableUser);
		
		if (result.hasErrors()) {
			model.addAttribute("activePage", "password");
			model.addAttribute("editableUser", editableUser);
			return "settings/password";
		}
		
		userService.updateUserPassword(passwordSettingsForm);
		model.addAttribute("userUpdated", true);
		
		return "redirect:/settings/password";
	}

	
	@RequestMapping(value = "/skills", method = RequestMethod.GET)
	@PreAuthorize("#editableUser == principal.username or hasRole('HR')")
	public String showSkillsSettings(
			@RequestParam("editableUser") String editableUser, 
			@RequestParam(name = "userUpdated", required = false, defaultValue = "false") Boolean userUpdated,
			Model model) {
		
		model.addAttribute("activePage", "skills");
		model.addAttribute("editableUser", editableUser);
		model.addAttribute("userUpdated", userUpdated);
		
		List<SkillWithSelection> allSkillsWithSelectionForUser = 
				skillService.findAllSkillsSelectedForUser(editableUser);
			
		model.addAttribute("skillsSettingsForm", new SkillSettingsForm(allSkillsWithSelectionForUser));
		
		return "settings/skills";
	}
	
	@RequestMapping(value = "/skills", method = RequestMethod.POST)
	@PreAuthorize("#editableUser == principal.username or hasRole('HR')")
	public String updateSkillsSettings(
			@RequestParam("editableUser") String editableUser,
			@ModelAttribute SkillSettingsForm skillsSettingsForm,
			BindingResult result,
			Model model) {
		
		model.addAttribute("editableUser", editableUser);
		
		if (result.hasErrors()) {
			model.addAttribute("activePage", "skills");
			model.addAttribute("editableUser", editableUser);
			return "settings/skills";
		}
		
		User user = userService.findUserByUsername(editableUser);
		
		Set<Skill> savedSkills = skillsSettingsForm.getSkills().stream()
				.filter(k -> k.getSelected())
				.map(k -> k.getSkillId())
				.map(skillService::findSkillById)
				.collect(Collectors.toSet());
		
		user.setSkills(savedSkills);
		userService.save(user);
		model.addAttribute("userUpdated", true);
		
		return "redirect:/settings/skills";
	}
}
