package com.example.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/message") // client sends message to /app/message
    @SendTo("/topic/messages")  // server sends to all subscribers of /topic/messages
    public String sendMessage(String message) {
        System.out.println("Received: " + message);
        return message; // broadcast same message back
    }
}
