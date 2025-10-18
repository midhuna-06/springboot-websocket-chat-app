package com.example.chat.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.chat.model.Message;
import com.example.chat.service.ChatService;

@Controller
public class ChatController {

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(Message message) {
        return message;
    }

    @MessageMapping("/newUser")
    @SendTo("/topic/users")
    public List<String> newUser(Message message) {
        ChatService.addUser(message.getFrom());
        return ChatService.getAllUsers();
    }

    @MessageMapping("/leaveUser")
    @SendTo("/topic/users")
    public List<String> leaveUser(Message message) {
        ChatService.removeUser(message.getFrom());
        return ChatService.getAllUsers();
    }
}
