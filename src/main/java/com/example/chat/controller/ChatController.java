package com.example.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.chat.model.Message;
import com.example.chat.service.UserService;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserService userService;

    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload Message message) {
        messagingTemplate.convertAndSend("/topic/public", message);
    }

    @MessageMapping("/newUser")
    public void newUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        // Save username in session
        headerAccessor.getSessionAttributes().put("username", message.getSender());

        // Add user to DB (online = true)
        userService.addUser(message.getSender());

        // Send updated list to all
        messagingTemplate.convertAndSend("/topic/users", userService.getOnlineUsers());
    }

    @MessageMapping("/disconnectUser")
    public void disconnectUser(@Payload Message message) {
        userService.removeUser(message.getSender());
        messagingTemplate.convertAndSend("/topic/users", userService.getOnlineUsers());
    }
}
