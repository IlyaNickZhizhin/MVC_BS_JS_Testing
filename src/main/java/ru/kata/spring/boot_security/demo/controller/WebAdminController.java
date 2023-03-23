package ru.kata.spring.boot_security.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class WebAdminController {

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public WebAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model, Authentication au) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        model.addAttribute("current", au.getPrincipal());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @GetMapping (value = "/admin/addNewUser")
    public String addNewUser(Model model, Authentication au) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("current", au.getPrincipal());
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
