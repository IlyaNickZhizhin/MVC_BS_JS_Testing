package ru.kata.spring.boot_security.demo;

import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public TestUserDataLoader(UserService userService, RoleService roleService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        if (userService.getAllUsers().size()==0) {
            userService.saveUser(new User("Ilya", "Zhizhin", "ilya@mail.ru",
                 "ilya", Set.of(new Role("ROLE_ADMIN"))));
            userService.saveUser(new User("Irina", "Zhizhina", "irina@mail.ru",
                    "irina", Set.of(new Role("ROLE_USER"))));
            userService.saveUser(new User("Stepan", "Pozdeev", "stepan@mail.ru",
                    "stepan", Set.copyOf(roleService.getAllRoles())));
            userService.saveUser(new User("Michail", "Pozdeev", "misha@mail.ru",
                    "misha", Set.of(roleService.getRoleById(2))));
            userService.saveUser(new User("Mark", "Zhizhin", "mark@mail.ru",
                    "mark", Set.of(roleService.getRoleById(1))));
        }
    }
}
