package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class WebUserController {

    UserService userService;

    @Autowired
    public WebUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/")
    public String showUserById(@RequestParam("id") int id,
                               Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/user-info";
    }

    @PatchMapping("/user/saveUser")
    public String updateUser(@ModelAttribute User user) {
        String pass = user.getPassword();
        int id = user.getId();
        user.setRoles(userService.getUserById(id).getRoles());
        user.setEnabled(userService.getUserById(id).isEnabled());
        if (!pass.equals(userService.getUserById(id).getPassword())) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPass = passwordEncoder.encode(pass);
            user.setPassword(hashedPass);
        }
        userService.saveUser(user);
        return "redirect:/user/?id=" + id;
    }

    @DeleteMapping(value = "/user/deleteUser")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/user/";
    }

}
