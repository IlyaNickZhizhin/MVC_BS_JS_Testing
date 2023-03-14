package ru.kata.spring.boot_security.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import ru.kata.spring.boot_security.demo.model.Role;
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
        model.addAttribute("current", getUser(au));
        return "/admin-all-users";
    }

    @GetMapping (value = "/admin/addNewUser")
    public String addNewUser(Model model, Authentication au) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("current", getUser(au));
        return "/admin-user-info";
    }

    @GetMapping("/admin/showUserById")
    public String showUserById(@RequestParam("id") int id,
                             Model model, Authentication au) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("current", getUser(au));
        return "/admin-user-info";
    }
    @PatchMapping("/admin/saveUser")
    public String updateUser(@ModelAttribute("changedUser") User user) {
        String pass = user.getPassword();
        int id = user.getId();
        if(userService.getUserById(id)==null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPass = passwordEncoder.encode(pass);
            user.setPassword(hashedPass);
            userService.saveUser(user);
            return "redirect:/admin";
        }
        if (!pass.equals(userService.getUserById(id).getPassword())) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPass = passwordEncoder.encode(pass);
            user.setPassword(hashedPass);
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/admin/deleteUser")
    public String deleteUser(@RequestParam(name = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/roles")
    public String showAllRoles(Model model) {
        List<Role> roleList = roleService.getAllRoles();
        model.addAttribute("roles", roleList);
        return "/admin-all-roles";
    }

    @PostMapping(value = "/admin/addNewRole")
    public String addNewRole(@RequestParam("authority") String authority) {
        Role role = new Role();
        role.setAuthority(authority);
        roleService.saveRole(role);
        return "redirect:/admin/roles";
    }

    @DeleteMapping(value = "/admin/deleteRole")
    public String deleteRole(@RequestParam(name = "id") int id) {
        roleService.deleteRole(id);
        return "redirect:/admin/roles";
    }

    private User getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return userService.getUserByEmail(username);
        }
        return null;
    }

}
