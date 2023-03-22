package ru.kata.spring.boot_security.demo.service;

import java.util.List;
import ru.kata.spring.boot_security.demo.model.User;


public interface UserService {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(int id);

    void deleteUser(int id);

}
