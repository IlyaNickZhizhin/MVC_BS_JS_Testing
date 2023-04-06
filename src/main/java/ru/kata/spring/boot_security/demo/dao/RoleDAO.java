package ru.kata.spring.boot_security.demo.dao;

import java.util.List;
import ru.kata.spring.boot_security.demo.model.Role;

public interface RoleDAO {

    List<Role> getAllRoles();

    Role getRoleById(int id);

}
