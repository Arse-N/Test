package com.example.testing.service.impl;

import com.example.testing.model.User;
import com.example.testing.repository.UserRepository;
import com.example.testing.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public void add(User user){
        userRepository.save(user);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @Override
    public ResponseEntity<?> deleteUser(User user) {
        userRepository.delete(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public User findById(long id) {
        Optional<User> byId = userRepository.findById(id);
        return byId.orElse(null);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public String findPhoneByName(String name) {
        Optional<User> byName = userRepository.findByName(name);
        return byName.get().getPhone();
    }
}
