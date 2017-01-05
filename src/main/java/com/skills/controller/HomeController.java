package com.skills.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String test(Principal principal, Model model) {
        return "home/home";
    }

    @RequestMapping("/login")
    public String login() {
        return "home/login";
    }
}
