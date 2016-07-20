package com.ness.app.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ness.app.service.AreaService;
import com.ness.app.service.UserService;
import com.ness.app.web.dto.AutocompleteDto;
import com.ness.app.web.dto.AutocompleteDto.AutocompleteChildrenDto;
import com.ness.app.web.dto.UserDto;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	private AreaService areaService;
	private UserService userService;
	
	@Autowired
	public SearchController(AreaService areaService, UserService userService) {
		this.areaService = areaService;
		this.userService = userService;
	}

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String showMainSearchView() {
		return "search/index";
	}
	
	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public @ResponseBody Set<UserDto> showSearchResult(@RequestBody List<AutocompleteChildrenDto> result) {
		Set<String> skills = result.stream()
				.map(k -> k.getText())
				.collect(Collectors.toSet());
		
		Set<UserDto> users = userService.findUsersWithSkills(skills).stream()
				.map(user -> new UserDto(user))
				.collect(Collectors.toSet());
		
		return users;
	}
	
	@RequestMapping(value = "/autocomplete", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<AutocompleteDto> getAllSkillsTitleForAutocomplete(final HttpServletResponse response) {
		return areaService.getAutocomplete();
	}
}
