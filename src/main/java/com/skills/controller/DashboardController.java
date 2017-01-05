package com.skills.controller;

import com.skills.domain.model.User;
import com.skills.service.AreaService;
import com.skills.service.SkillService;
import com.skills.service.UserService;
import com.skills.view.CreateUserForm;
import com.skills.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private UserService userService;
    private SkillService skillService;
    private AreaService areaService;

    @Autowired
    public DashboardController(UserService userService, SkillService skillService, AreaService areaService) {
        this.userService = userService;
        this.skillService = skillService;
        this.areaService = areaService;
    }

    @RequestMapping(value = {"", "/", "/users"}, method = RequestMethod.GET)
    public String showUsersDashboard(Model model) {
        model.addAttribute("createUserForm", new CreateUserForm());
        model.addAttribute("activePage", "users");
        return "dashboard/users";
    }

    @RequestMapping(value = "/new-user", method = RequestMethod.POST)
    public String createNewUser(
            @Valid @ModelAttribute("createUserForm") CreateUserForm createUserForm,
            BindingResult result,
            Model model) {

        model.addAttribute("activePage", "users");

        if (result.hasErrors()) {
            return "user/fragments/create-user-form :: create-user-form(url=@{/dashboard/new-user})";
        }

        User user = new User(createUserForm);
        model.addAttribute("createUserForm", new CreateUserForm());
        userService.save(user);

        return "user/fragments/create-user-form :: info-success";
    }

    @RequestMapping(value = "/table-of-users", method = RequestMethod.GET)
    public String getTableOfAllUsers(@RequestParam("page") int page, @RequestParam("size") int size, Model model) {
//		PageRequest pr = new PageRequest(page, size);
//		
//		Page<User> p = userService.findAllUsers(pr);
//		
//		model.addAttribute("totalPages", p.getTotalPages());

        List<UserDto> users = userService.findAllUsers().stream()
                .map(u -> new UserDto(u))
                .collect(Collectors.toList());

        model.addAttribute("users", users);

        return "dashboard/fragments/table-of-users :: table-of-users";
    }
}
