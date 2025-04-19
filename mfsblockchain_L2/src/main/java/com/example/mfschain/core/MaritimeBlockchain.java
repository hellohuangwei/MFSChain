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
            // 从数据库加载创世区块（如果有的话）
            MaritimeBlock genesisBlock = blockRepository.findTopByOrderByHeightDesc();
            if (genesisBlock == null) {
                // 如果没有创世区块，创建并保存
                genesisBlock = new MaritimeBlock(0, "GENESIS", "0");
                blockRepository.save(genesisBlock);
            }
            chain.add(genesisBlock);  // 将创世区块加入链中
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("初始化区块链时出错", e);
        }
    }

    // 获取指定范围内的区块
    public List<MaritimeBlock> getBlocksInRange(int fromIndex, int toIndex) {
        // 根据索引查询区块数据
        return blockRepository.findBlocksInRange( fromIndex,  toIndex);
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
