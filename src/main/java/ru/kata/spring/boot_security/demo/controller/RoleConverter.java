package ru.kata.spring.boot_security.demo.controller;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.RoleService;

@Controller
public class RoleConverter implements Converter<String, Role>{

    RoleService roleService;

    public RoleConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public Role convert(String id) {
        return roleService.getRoleById(Integer.parseInt(id));
    }
}
