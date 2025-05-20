package com.example.mfschain.core;

import com.example.mfschain.data.MaritimeBlock;
import com.example.mfschain.data.MaritimeBlockRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaritimeBlockchain {

    @Autowired
    private MaritimeBlockRepository blockRepository;

    private List<MaritimeBlock> chain = new ArrayList<>();

    public MaritimeBlockchain() {

    }

    @PostConstruct
    public void init() {
        try {
            // Load genesis block from database (if exists)
            MaritimeBlock genesisBlock = blockRepository.findTopByOrderByHeightDesc();
            if (genesisBlock == null) {
                // If no genesis block exists, create and save one
                genesisBlock = new MaritimeBlock(0, "GENESIS", "0");
                blockRepository.save(genesisBlock);
            }
            chain.add(genesisBlock);  // Add genesis block to the chain
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error initializing blockchain", e);
        }
    }

    // Get blocks within specified range
    public List<MaritimeBlock> getBlocksInRange(int fromIndex, int toIndex) {
        // Query block data based on index range
        return blockRepository.findBlocksInRange(fromIndex, toIndex);
    }

    public MaritimeBlock addBlock(String maritimeRootHash) {
        MaritimeBlock latestBlock = getLatestBlock();
        int newIndex = latestBlock.getHeight() + 1;
        String previousHash = latestBlock.getHash();
        MaritimeBlock newBlock = new MaritimeBlock(newIndex, maritimeRootHash, previousHash);
        chain.add(newBlock);
        blockRepository.save(newBlock);
        return newBlock;
    }

    public MaritimeBlock getLatestBlock() {
        return chain.get(chain.size() - 1);
    }
}
