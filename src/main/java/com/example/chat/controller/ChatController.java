package com.example.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.chat.model.Message;

@Controller
public class ChatController {

    // Serve the HTML chat page when user visits /chat
    @GetMapping("/chat")
    public String chatPage() {
        return "chat"; // this loads chat.html from src/main/resources/templates/
    }

    // Handle messages sent from client (at /app/sendMessage)
    @MessageMapping("/sendMessage")
    @SendTo("/topic/public") // broadcast to everyone subscribed
    public Message sendMessage(Message message) {
        // Optionally, we can add timestamp if missing
        if (message.getTime() == null || message.getTime().isEmpty()) {
            message.setTime(java.time.LocalTime.now().withNano(0).toString());
        }
        return message;
    }
}
