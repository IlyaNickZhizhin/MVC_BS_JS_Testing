package ru.kata.spring.boot_security.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="authorities")
public class Role implements GrantedAuthority {

    @Id
    @Column(name="username")
    private String username;

    @Column(name="authority")
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
