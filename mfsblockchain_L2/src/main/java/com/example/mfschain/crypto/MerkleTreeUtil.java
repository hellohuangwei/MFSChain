package com.example.mfschain.crypto;

import com.example.mfschain.data.MaritimeBlockData;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class MerkleTreeUtil {

    // Method to generate Merkle tree root hash and verification paths
    public MaritimeBlockData generateMerkleTree(List<String> dataBlocks) {
        List<String> leafNodes = new ArrayList<>();

        // Step 1: Hash each data block and create leaf nodes
        for (String data : dataBlocks) {
            leafNodes.add(hash(data));
        }

        // Step 2: Build the Merkle tree
        List<String> treeLayer = leafNodes;
        List<String> verificationPaths = new ArrayList<>();

        while (treeLayer.size() > 1) {
            List<String> newLayer = new ArrayList<>();

            for (int i = 0; i < treeLayer.size(); i += 2) {
                if (i + 1 < treeLayer.size()) {
                    String combinedHash = hash(treeLayer.get(i) + treeLayer.get(i + 1));
                    newLayer.add(combinedHash);
                    
                    // Add the path for verification
                    verificationPaths.add(treeLayer.get(i + 1));
                } else {
                    newLayer.add(treeLayer.get(i));  // Handle odd number of elements
                }
            }
            treeLayer = newLayer;
        }

        // Step 3: The root hash is the last remaining element in the tree
        String rootHash = treeLayer.get(0);
        return new MaritimeBlockData("",rootHash, verificationPaths);
    }

    // Utility method to hash data
    private String hash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(data.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }
}
