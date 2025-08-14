package com.example.hibernate_flyway_example.service.impl;

import com.example.hibernate_flyway_example.dto.UserDto;
import com.example.hibernate_flyway_example.entity.User;
import com.example.hibernate_flyway_example.repository.UserRepository;
import com.example.hibernate_flyway_example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
