package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    void saveUser(User user);
    void deleteUser(Long id);
    List<User> getAllUsers();
    User getUserById(Long id);
    User loadUserByUsername(String username);
}

