package ru.kata.spring.boot_security.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class RESTController {

    private final UserService userService;

    @Autowired
    public RESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        List<User> userList = userService.getAllUsers();
        return userList;
    }
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        return user;
    }

    @PostMapping("/users")
        public User addUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "User with ID = " + id + " was deleted";
    }

}
