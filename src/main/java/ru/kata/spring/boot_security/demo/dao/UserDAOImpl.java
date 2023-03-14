package ru.kata.spring.boot_security.demo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

@Repository
public class UserDAOImpl implements UserDAO{

    private final EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(int id) {
        Query query = entityManager.createQuery("delete from User where id = :userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }

    @Override
    public User getUserByEmail(String email) {
       return entityManager.createQuery("from User where email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public User getUserByEmailWithRoles(String email) {
        String query = "select distinct u from User u "
                + "left join fetch u.roles "
                + "where u.email = :email";
        return entityManager.createQuery(query, User.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
