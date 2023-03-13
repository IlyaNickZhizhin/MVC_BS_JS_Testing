package ru.kata.spring.boot_security.demo;

import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


@Component
public class
TestUserDataLoader {

    private final UserService userService;
    private final RoleService roleService;
    private final static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public TestUserDataLoader(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        userService.saveUser(new User("One", "One", "one@mail.ru",
                encoder.encode("one"), true, Set.of(new Role("ROLE_ADMIN"))));
        userService.saveUser(new User("Two", "Two", "two@mail.ru",
                encoder.encode("two"), true, Set.of(new Role("ROLE_USER"))));
        userService.saveUser(new User("Three", "Three", "three@mail.ru",
                encoder.encode("three"), true, Set.copyOf(roleService.getAllRoles())));
    }
}
