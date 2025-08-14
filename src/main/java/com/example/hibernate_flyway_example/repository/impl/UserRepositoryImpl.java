package com.example.hibernate_flyway_example.repository.impl;


import com.example.hibernate_flyway_example.dto.OrderDto;
import com.example.hibernate_flyway_example.dto.UserDto;
import com.example.hibernate_flyway_example.entity.User;
import com.example.hibernate_flyway_example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    @Override
    public User save(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            if (user.getId() == null) {
                session.persist(user); // insert
            } else {
                session.merge(user); // update
            }
            tx.commit();
            return user;
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            return Optional.ofNullable(user);
        }
    }

    @Override
    public List<UserDto> findAll() {
        try (Session session = sessionFactory.openSession()) {
            List<User> users = session.createQuery(
                            "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.orders", User.class)
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
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.remove(user);
            }
            tx.commit();
        }
    }
}
