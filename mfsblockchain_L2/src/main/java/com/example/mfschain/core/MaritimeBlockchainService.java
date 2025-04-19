package com.example.mfschain.core;

import com.example.mfschain.data.MaritimeBlock;
import com.example.mfschain.data.MaritimeBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaritimeBlockchainService {

    @Autowired
    private MaritimeBlockRepository blockRepository;

    /**
     * Initializes the genesis block (the first block in the chain).
     * This block is added to the database if the chain is empty.
     */
    public void initializeChain() {
        // If there are no blocks in the chain, create and save the genesis block
        if (blockRepository.count() == 0) {
            MaritimeBlock genesis = new MaritimeBlock(0, "GENESIS_B2", "0"); // Genesis block with height 0
            blockRepository.save(genesis);
        }
    }

    /**
     * Adds a new block to the blockchain.
     * The new block is created with the root hash provided, using the previous block's hash.
     *
     * @param rootHash The root hash to be used in the new block
     * @return The newly added block
     */
    public MaritimeBlock addBlock(String rootHash) {
        // Get the latest block in the chain
        MaritimeBlock latest = getLatestBlock();
        // The height of the new block is one more than the latest block
        int newHeight =  (latest.getHeight() + 1);
        // Create a new block with the provided root hash and the previous block's hash
        MaritimeBlock newBlock = new MaritimeBlock(newHeight, rootHash, latest.getHash());
        // Save the new block to the repository
        return blockRepository.save(newBlock);
    }

    /**
     * Retrieves the latest block in the blockchain.
     *
     * @return The latest block
     */
    public MaritimeBlock getLatestBlock() {
        // Retrieve the block with the highest height (most recent block)
        return blockRepository.findTopByOrderByHeightDesc();
    }

    /**
     * Retrieves the entire blockchain.
     *
     * @return List of all blocks in the blockchain
     */
    public List<MaritimeBlock> getBlockchain() {
        // Return all blocks from the repository
        return blockRepository.findAll();
    }

    /**
     * Validates the integrity of the blockchain.
     * Checks if the hashes in the chain are consistent and if each block's previous hash matches the previous block's hash.
     *
     * @return True if the blockchain is valid, otherwise false
     */
    public boolean isChainValid() {
        List<MaritimeBlock> chain = getBlockchain();
        // Start from the second block (index 1) and check each block against the previous one
        for (int i = 1; i < chain.size(); i++) {
            MaritimeBlock current = chain.get(i);
            MaritimeBlock previous = chain.get(i - 1);

            // Calculate the expected hash for the current block
            String expectedHash = current.calculateHash();
            // Check if the current block's hash or previous hash is invalid
            if (!current.getHash().equals(expectedHash) ||
                    !current.getPreviousHash().equals(previous.getHash())) {
                return false;
            }
        }
        return true;
    }
}
