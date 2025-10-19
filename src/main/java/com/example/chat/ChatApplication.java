package com.example.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.chat")
@EntityScan(basePackages = "com.example.chat.model")
@EnableJpaRepositories(basePackages = "com.example.chat.repository")
public class ChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
        System.out.println("🚀 WebSocket Chat App running on http://localhost:8080/chat.html");
    }
}
