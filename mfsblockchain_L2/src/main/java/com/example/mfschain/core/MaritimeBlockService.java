package com.example.mfschain.core;

import com.example.mfschain.data.MaritimeBlock;
import com.example.mfschain.data.MaritimeBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaritimeBlockService {

    @Autowired
    private MaritimeBlockRepository maritimeBlockRepository;

    /**
     * Creates a new block after consensus has been reached.
     * @param newRootHash The Merkle root hash of the new block.
     * @param previousBlock The previous block in the chain.
     * @return The newly created block.
     */
    public MaritimeBlock createNewBlock(String newRootHash, MaritimeBlock previousBlock) {
        // Get the previous block's height
        int newHeight = previousBlock.getHeight() + 1;

        // Create a new block using the previous block's hash and the new root hash
        MaritimeBlock newBlock = new MaritimeBlock(newHeight, newRootHash, previousBlock.getHash());

        // Save the new block to the repository
        maritimeBlockRepository.save(newBlock);

        // Here, you would add broadcasting logic, e.g., using WebSocket, HTTP API, or P2P communication.
        broadcastNewBlock(newBlock);

        return newBlock;
    }

    /**
     * Broadcast the newly created block to other nodes in the network.
     * @param newBlock The new block to be broadcast.
     */
    private void broadcastNewBlock(MaritimeBlock newBlock) {
        // This is a simplified broadcast logic.
        // In a real-world scenario, you would use a messaging system, WebSockets, or P2P communication to send the block to other nodes.
        System.out.println("Broadcasting new block with height: " + newBlock.getHeight() + " and hash: " + newBlock.getHash());

        // Example of sending the block to other nodes (simplified version)
        // For example, you could make a REST API call or a WebSocket push to notify other nodes.
    }

    /**
     * Retrieves the most recent block in the chain.
     * @return The most recent block or null if no blocks exist.
     */
    public Optional<MaritimeBlock> getLatestBlock() {
        return maritimeBlockRepository.findTopByOrderByHeightDesc();
    }
}
