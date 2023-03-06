package ru.kata.spring.boot_security.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class WebAdminController {

    private final UserService userService;

    @Autowired
    public WebAdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        return "/admin-all-users";
    }

    @PostMapping(value = "/admin/addNewUser")
    public String addNewUser(@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/showUserById")
    public String showUserById(@RequestParam("id") int id,
                             Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/admin-user-info";
    }
    @PatchMapping("/admin/saveUser")
    public String updateUser(@ModelAttribute("changedUser") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/admin/deleteUser")
    public String deleteUser(@RequestParam(name = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
