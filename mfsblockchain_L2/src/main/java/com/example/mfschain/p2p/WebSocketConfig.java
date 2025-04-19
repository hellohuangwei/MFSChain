package com.example.mfschain.p2p;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        // Register the handler for Blockchain data
        registry.addHandler(new BlockchainWebSocketHandler(), "/ws/blockchain")
                .addInterceptors(new HttpSessionHandshakeInterceptor())  // Optionally add interceptors to manage sessions
                .setAllowedOrigins("*"); // Allow cross-origin requests

        // Register the handler for Maritime data
        registry.addHandler(new MaritimeDataWebSocketHandler(), "/ws/maritimedata")
                .addInterceptors(new HttpSessionHandshakeInterceptor())  // Optionally add interceptors to manage sessions
                .setAllowedOrigins("*"); // Allow cross-origin requests
    }
}
