package com.example.mfschain.p2p;

import com.example.mfschain.core.MaritimeDataBlockService;
import com.example.mfschain.data.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MaritimeDataWebSocketHandler extends TextWebSocketHandler {


    final private MerkleTreeService merkleTreeService = new MerkleTreeService(); // Service for Merkle tree operations

    final static private MaritimeDataBlockService maritimeDataBlockService = new MaritimeDataBlockService();
    @Autowired
    private ApplicationContext context;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Parse the received message into MaritimeData
        MaritimeData maritimeData = parseMessage(message.getPayload());

//         Generate Merkle root hash and verification paths
        MerkleResult result = merkleTreeService.calculateMerkleRootHash(maritimeData);
        System.out.println("Root hash: " + result.getRootHash());
        String leafHash = merkleTreeService.calculateHash(maritimeData.getVesselId());
        List<List<String>> verificationPath = result.getVerificationPaths(); // Example path for leaf 0
        System.out.println("Verification path: " + verificationPath);

// Verify the data using the leaf hash and root hash
        boolean isValid = merkleTreeService.verifyData(leafHash, result.getRootHash(), verificationPath);

        if (isValid) {
            System.out.println("Data is valid!");
        } else {
            System.out.println("Data is isvalid!");
        }
        MaritimeBlockData maritimeDataBlock = new MaritimeBlockData();
        maritimeDataBlock.setVesselId(maritimeData.getVesselId());
        maritimeDataBlock.setRootHash(result.getRootHash());
        maritimeDataBlock.setVerificationPaths(result.getVerificationPaths());
        // Save MaritimeBlockData to database
        maritimeDataBlockService.saveMaritimeDataBlock(maritimeDataBlock);

        // Optionally, send a response to the client with the root hash and verification paths
        session.sendMessage(new TextMessage("Merkle Root Hash and Verification Paths saved."));
    }

    // Method to parse the WebSocket message into MaritimeData object
    private MaritimeData parseMessage(String messagePayload) {
        // Assume a JSON parsing logic here to convert the message into MaritimeData object
        // This is a placeholder logic and should be replaced with actual JSON parsing.
        return new MaritimeData("SHIP123", "LAT: 35.6895, LON: 139.6917", "25 knots", "1625237261000");
    }
}
