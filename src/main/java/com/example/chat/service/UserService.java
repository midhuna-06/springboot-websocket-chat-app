// UserService.java
package com.example.chat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.chat.model.User;
import com.example.chat.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public void addUser(String username) {
        User existing = repo.findByUsername(username);
        if (existing == null) {
            repo.save(new User(username, true));
        } else {
            existing.setOnline(true);
            repo.save(existing);
        }
    }

    public void removeUser(String username) {
        User existing = repo.findByUsername(username);
        if (existing != null) {
            existing.setOnline(false);
            repo.save(existing);
        }
    }

    public List<String> getOnlineUsers() {
        return repo.findAll()
                   .stream()
                   .filter(User::isOnline)
                   .map(User::getUsername)
                   .toList();
    }
}
