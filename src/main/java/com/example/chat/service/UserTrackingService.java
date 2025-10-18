package com.example.chat.service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class UserTrackingService {
    private final Set<String> users = ConcurrentHashMap.newKeySet();

    public void addUser(String username) {
        users.add(username);
    }

    public void removeUser(String username) {
        users.remove(username);
    }

    public Set<String> getAllUsers() {
        return users;
    }
}
