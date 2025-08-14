package com.example.hibernate_flyway_example.repository.impl;

import com.example.hibernate_flyway_example.dto.OrderDto;
import com.example.hibernate_flyway_example.dto.UserDto;
import com.example.hibernate_flyway_example.entity.User;
import com.example.hibernate_flyway_example.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        if(user.getId() == null) {
            entityManager.persist(user);  // insert
        } else {
            user = entityManager.merge(user);  // update
        }
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = entityManager.createQuery("select u from User u", User.class)
                .getResultList();
        return users.stream()
                .map(u -> new UserDto(
                        u.getId(),
                        u.getName(),
                        u.getEmail(),
                        u.getOrders().stream()
                                .map(o -> new OrderDto(o.getId(), o.getName()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        var user = entityManager.find(User.class, id);
        if(user != null) {
            entityManager.remove(user);
        }
    }
}
