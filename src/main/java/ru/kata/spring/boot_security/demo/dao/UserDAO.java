package ru.kata.spring.boot_security.demo.dao;

import java.util.List;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserDAO {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(int id);

    void deleteUser(int id);

    int getIdByEmail(String email);

}
