package com.example.mfschain.rpc;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;


@RestController
@ServerEndpoint("/blockchain")
public class BlockchainController {

    // 处理 WebSocket 连接和消息
    private static WebSocketSession session;

    @OnOpen
    public void onOpen(Session session) {
        BlockchainController.session = (WebSocketSession) session;
        System.out.println("New WebSocket connection established.");
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
        // Handle the incoming message and potentially broadcast a new block
    }

    @OnClose
    public void onClose() {
        System.out.println("WebSocket connection closed.");
    }

}
