package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class WebUserController {

    UserService userService;

    @Autowired
    public WebUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/")
    public String showUserById(Authentication au,
                               Model model) {
        model.addAttribute("current", au.getPrincipal());
        return "user";
    }
}
