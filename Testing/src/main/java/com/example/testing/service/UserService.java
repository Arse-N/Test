package com.example.testing.service;

import com.example.testing.model.User;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

public interface UserService {
    void add(User user);

    ResponseEntity<?> deleteUser(User user);

    User findById(long id);

    void saveUser(User user);

    List<User> getAll();

    String findPhoneByName(String name);
}
