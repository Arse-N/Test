package com.example.testing.repository;

import com.example.testing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long id);

    Optional<User> findByName(String name);

    boolean existsById(Long id);
}
