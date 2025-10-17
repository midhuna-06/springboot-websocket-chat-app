package com.example.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration // Marks this as a configuration class
@EnableWebSocketMessageBroker // Enables WebSocket message handling using STOMP
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Defines the endpoint for WebSocket connection
        registry.addEndpoint("/ws").withSockJS();
        // "/ws" -> frontend will connect using this endpoint
        // .withSockJS() -> fallback for browsers that don’t support WebSocket
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Prefix for messages going from server → client
        registry.enableSimpleBroker("/topic");
        // Prefix for messages coming from client → server
        registry.setApplicationDestinationPrefixes("/app");
    }
}
