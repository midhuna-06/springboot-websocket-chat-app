// UserRepository.java
package com.example.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chat.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
