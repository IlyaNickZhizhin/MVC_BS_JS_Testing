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
        if (userService.getAllUsers().size()==0) {
            userService.saveUser(new User("Ilya", "Zhizhin", "ilya@mail.ru",
                    encoder.encode("ilya"), true, Set.of(new Role("ROLE_ADMIN"))));
            userService.saveUser(new User("Irina", "Zhizhina", "irina@mail.ru",
                    encoder.encode("irina"), true, Set.of(new Role("ROLE_USER"))));
            userService.saveUser(new User("Stepan", "Pozdeev", "stepan@mail.ru",
                    encoder.encode("stepan"), true, Set.copyOf(roleService.getAllRoles())));
            userService.saveUser(new User("Michail", "Pozdeev", "misha@mail.ru",
                    encoder.encode("misha"), false, Set.of(roleService.getRoleById(2))));
            userService.saveUser(new User("Mark", "Zhizhin", "mark@mail.ru",
                    encoder.encode("mark"), false, Set.of(roleService.getRoleById(1))));
        }
    }
}
