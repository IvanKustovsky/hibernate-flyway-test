package com.example.hibernate_flyway_example.repository;


import com.example.hibernate_flyway_example.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    void deleteById(Long id);
}
