package com.example.mfschain.data;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

@Service
public class MerkleTreeService {

    // Method to generate Merkle root hash and verification paths from maritime data
    public MerkleResult calculateMerkleRootHash(MaritimeData maritimeData) {
        // Convert maritime data into individual hashes (using shipId, position, speed, timestamp)
        List<String> dataChunks = new ArrayList<>();
        dataChunks.add(calculateHash(maritimeData.getVesselId()));
        dataChunks.add(calculateHash(maritimeData.getPosition()));
        dataChunks.add(calculateHash(maritimeData.getSpeed()));
        dataChunks.add(calculateHash(maritimeData.getTimestamp()));

        // Step 1: Build the Merkle tree and generate root hash and paths
        return buildMerkleTree(dataChunks);
    }

    // Method to build the Merkle tree and generate root hash and verification paths
    private MerkleResult buildMerkleTree(List<String> dataChunks) {
        List<String> currentLevel = dataChunks;
        List<List<String>> allPaths = new ArrayList<>();

        // Step 2: While we have more than one hash, continue building the tree
        while (currentLevel.size() > 1) {
            List<String> nextLevel = new ArrayList<>();
            List<String> paths = new ArrayList<>();

            // Merge pairs of hashes to create the next level of the tree
            for (int i = 0; i < currentLevel.size(); i += 2) {
                if (i + 1 < currentLevel.size()) {
                    // Concatenate two adjacent hashes
                    String concatenated = currentLevel.get(i) + currentLevel.get(i + 1);
                    nextLevel.add(calculateHash(concatenated));
                    paths.add(currentLevel.get(i + 1)); // Path for verification
                } else {
                    // Single element, no pair to combine, carry it up as is
                    nextLevel.add(currentLevel.get(i));
                    paths.add(""); // No path needed for the last odd hash
                }
            }
            currentLevel = nextLevel;
            allPaths.add(paths); // Store the paths for the current level
        }

        // The last remaining element in currentLevel is the Merkle root
        String rootHash = currentLevel.get(0);

        // Return the Merkle root hash and the verification paths
        return new MerkleResult(rootHash, allPaths);
    }

    // Method to calculate hash using SHA-256
    public String calculateHash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error while calculating hash", e);
        }
    }

    // Method to verify data using Merkle root and verification path
    public boolean verifyData(String leafHash, String rootHash, List<List<String>> verificationPath) {
        String calculatedHash = leafHash;

        // Traverse the verification path and compute the hash to get the Merkle root
        for (List<String> siblingHash : verificationPath) {
            // If siblingHash is not empty, we need to concatenate the current hash with the sibling hash
            if (!siblingHash.isEmpty()) {
                String concatenated = calculatedHash + siblingHash;
                calculatedHash = calculateHash(concatenated);
            }
        }

        // Finally, compare the calculated root hash with the provided root hash
        return calculatedHash.equals(rootHash);
    }
}