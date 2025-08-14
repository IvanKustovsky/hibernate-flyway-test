package com.example.hibernate_flyway_example.service;

import com.example.hibernate_flyway_example.dto.UserDto;
import com.example.hibernate_flyway_example.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User create(User user);

    Optional<User> getById(Long id);

    List<UserDto> getAll();

    void delete(Long id);
}
