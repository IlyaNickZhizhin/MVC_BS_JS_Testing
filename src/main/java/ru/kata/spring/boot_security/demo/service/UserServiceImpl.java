package ru.kata.spring.boot_security.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        String pass = user.getPassword();
        int id = user.getId();
        if (userDAO.getUserById(id) == null) {
            String hashedPass = passwordEncoder.encode(pass);
            user.setPassword(hashedPass);
            userDAO.saveUser(user);
            return;
        }
        if (!pass.equals(userDAO.getUserById(id).getPassword())) {
            String hashedPass = passwordEncoder.encode(pass);
            user.setPassword(hashedPass);
        }
        userDAO.saveUser(user);
    }

    @Transactional
    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }


    @Override
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

}
