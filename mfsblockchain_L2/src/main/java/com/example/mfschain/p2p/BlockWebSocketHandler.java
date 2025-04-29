package com.example.mfschain.p2p;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class BlockWebSocketHandler extends TextWebSocketHandler {

    // List of connected WebSocket sessions (clients)
    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Add the new session to the list of sessions
        sessions.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, org.springframework.web.socket.TextMessage message) throws Exception {
        // You can handle incoming messages if necessary (not needed for broadcasting).
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        // Remove the session when a client disconnects
        sessions.remove(session);
    }

    /**
     * Method to broadcast a new block message to all connected clients.
     * @param blockData The data of the new block to be broadcasted
     */
    public void broadcastNewBlock(String blockData) {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new org.springframework.web.socket.TextMessage(blockData));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
