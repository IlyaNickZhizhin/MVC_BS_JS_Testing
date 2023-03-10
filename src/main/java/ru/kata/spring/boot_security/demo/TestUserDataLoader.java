//package ru.kata.spring.boot_security.demo;
//
//import java.util.Set;
//import javax.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import ru.kata.spring.boot_security.demo.model.Role;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//
//@Component
//public class
//TestUserDataLoader {
//
//    private final UserService userService;
//
//    @Autowired
//    public TestUserDataLoader(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostConstruct
//    public void init() {
//        User user1 = new User();
//        User user2 = new User();
//        User user3 = new User();
//        Role roleUser = new Role();
//        Role roleAdmin = new Role();
//        roleUser.setAuthority("ROLE_USER");
//        roleAdmin.setAuthority("ROLE_ADMIN");
//        Set<Role> onlyUser = Set.of(roleUser);
//        Set<Role> onlyAdmin = Set.of(roleAdmin);
//        Set<Role> userAndAdmin = Set.of(roleUser, roleAdmin);
//        user1.setName("user1");
//        user1.setEmail("user1@mail.ru");
//        user1.setPassword("user1");
//        user1.setRoles(onlyUser);
//        user1.setEnabled(true);
//        user2.setName("user2");
//        user2.setEmail("user2@mail.ru");
//        user2.setPassword("user2");
//        user2.setRoles(userAndAdmin);
//        user2.setEnabled(true);
//        user3.setName("user3");
//        user3.setEmail("user3@mail.ru");
//        user3.setPassword("user3");
//        user3.setRoles(onlyAdmin);
//        user3.setEnabled(true);
//        userService.saveUser(user1);
//        userService.saveUser(user2);
//        userService.saveUser(user3);
//    }
//}
