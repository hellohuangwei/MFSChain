package com.example.mfschain.p2p;

import com.example.mfschain.core.MaritimeBlockchain;
import com.example.mfschain.data.MaritimeBlock;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class BlockchainWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private MaritimeBlockchain blockchain;  // Blockchain service to interact with the blockchain

    private final WebSocketConnectionManager connectionManager;  // Manages WebSocket connections

    // Constructor to initialize the connection manager
    public BlockchainWebSocketHandler() {
        this.connectionManager = new WebSocketConnectionManager();  // Initialize WebSocket connection manager
    }

    //定时查询数据库里面区块的数据，然后批量转发到其他节点，进行批量验证，验证通过的节点进行投票算法


    /**
     * Handles block synchronization requests.
     * Requests for missing blocks from a specific range (fromIndex to toIndex).
     *
     * @param session WebSocket session
     * @param msg JSON object containing sync request data
     */
    private void handleSyncRequest(WebSocketSession session, JSONObject msg) {
        int fromIndex = msg.getJSONObject("data").getInt("fromIndex");
        int toIndex = msg.getJSONObject("data").getInt("toIndex");

        // Get the missing blocks from the blockchain
        List<MaritimeBlock> blocksToSync = blockchain.getBlocksInRange(fromIndex, toIndex);

        // Prepare response with the missing blocks
        JSONObject response = new JSONObject();
        response.put("type", "BLOCK_SYNC_RESPONSE");
        response.put("data", blocksToSync);

        // Send the response with missing blocks
        sendMessage(session, response.toString());
    }

    /**
     * Handles block synchronization responses.
     * This method processes incoming blocks and adds them to the blockchain.
     *
     * @param msg JSON object containing block sync response data
     */
    private void handleSyncResponse(JSONObject msg) {
        List<MaritimeBlock> blocks = new ArrayList<>();
        try {
            // Use ObjectMapper to convert the JSON array into a list of MaritimeBlock objects
            ObjectMapper objectMapper = new ObjectMapper();
            blocks = objectMapper.readValue(msg.getJSONArray("data").toString(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, MaritimeBlock.class));

            // Add the blocks to the blockchain
            for (MaritimeBlock block : blocks) {
                blockchain.addBlock(block.getRootHash());
            }
        } catch (Exception e) {
            e.printStackTrace();  // Handle JSON parsing error
        }
    }

    /**
     * Sends a message to the WebSocket client.
     *
     * @param session WebSocket session
     * @param message The message to be sent
     */
    private void sendMessage(WebSocketSession session, String message) {
        try {
            session.sendMessage(new TextMessage(message));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles incoming WebSocket text messages.
     * This method processes block synchronization requests, responses, and heartbeat messages.
     *
     * @param session WebSocket session
     * @param message The incoming WebSocket message
     */
    @Override
    public void handleTextMessage(WebSocketSession session, org.springframework.web.socket.TextMessage message) {
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);

        // Heartbeat check: if the payload is "heartbeat", send a heartbeat response
        if (payload.equals("heartbeat")) {
            sendHeartbeatResponse(session);
        } else if (payload.startsWith("BLOCK_SYNC_REQUEST")) {
            // Handle block sync requests
            handleSyncRequest(session, new JSONObject(payload));
        } else if (payload.startsWith("BLOCK_SYNC_RESPONSE")) {
            // Handle block sync responses
            handleSyncResponse(new JSONObject(payload));
        }
    }

    /**
     * Called when a WebSocket connection is established.
     * Adds the session to the connection manager.
     *
     * @param session The WebSocket session
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("Connection established, sessionId: " + session.getId());

        // Add the session to the connection manager (if applicable)
        connectionManager.addSession(session);
    }

    /**
     * Sends a heartbeat response back to the client.
     * This helps maintain an active connection.
     *
     * @param session WebSocket session
     */
    private void sendHeartbeatResponse(WebSocketSession session) {
        try {
            session.sendMessage(new TextMessage("heartbeat_response"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when the WebSocket connection is closed.
     * Removes the session from the connection manager.
     *
     * @param session WebSocket session
     * @param status Close status
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
        System.out.println("Connection closed, sessionId: " + session.getId());

        // Handle node disconnection and remove from the connection manager
        connectionManager.removeSession(session);  // Remove invalid session
    }

    /**
     * Starts a background thread to monitor and send heartbeat messages at regular intervals.
     *
     * @param session WebSocket session
     */
    public void startHeartbeatMonitor(WebSocketSession session) {
        new Thread(() -> {
            while (session.isOpen()) {
                try {
                    // Send a heartbeat message every 5 seconds
                    sendMessage(session, "heartbeat");
                    Thread.sleep(5000);  // Send heartbeat every 5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
