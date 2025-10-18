package com.example.chat.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.chat.model.Message;
import com.example.chat.service.UserService;

@Controller
public class ChatController {

    private final UserService userService;

    public ChatController(UserService userService) {
        this.userService = userService;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(Message message) {
        return message;
    }

    @MessageMapping("/newUser")
    @SendTo("/topic/users")
    public List<String> newUser(Message message) {
        userService.addUser(message.getSender());
        return userService.getOnlineUsers();
    }

    @MessageMapping("/disconnectUser")
    @SendTo("/topic/users")
    public List<String> disconnectUser(Message message) {
        userService.removeUser(message.getSender());
        return userService.getOnlineUsers();
    }
}
