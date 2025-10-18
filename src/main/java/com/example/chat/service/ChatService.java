package com.example.chat.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private static final Set<String> activeUsers = Collections.synchronizedSet(new HashSet<>());

    public static void addUser(String name) { activeUsers.add(name); }

    public static void removeUser(String name) { activeUsers.remove(name); }

    public static List<String> getAllUsers() { return new ArrayList<>(activeUsers); }
}
