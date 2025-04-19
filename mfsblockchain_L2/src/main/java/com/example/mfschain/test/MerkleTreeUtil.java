package com.example.mfschain.test;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class MerkleTreeUtil {

    // Compute the SHA-256 hash of the data
    public static String hash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(data.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));  // Convert each byte to hexadecimal format
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error while hashing data", e);  // Handle exception if something goes wrong
        }
    }

    // Build the Merkle tree from a list of data blocks
    public static String buildMerkleTree(List<String> dataBlocks) {
        List<String> hashes = new ArrayList<>();

        // Step 1: Compute the hash for each data block
        for (String dataBlock : dataBlocks) {
            hashes.add(hash(dataBlock));  // Store the hash of each data block
        }

        // Step 2: Build tree levels until only one root hash remains
        while (hashes.size() > 1) {
            List<String> newHashes = new ArrayList<>();
            for (int i = 0; i < hashes.size(); i += 2) {
                if (i + 1 < hashes.size()) {
                    // If there are two adjacent nodes, combine and hash them
                    newHashes.add(hash(hashes.get(i) + hashes.get(i + 1)));
                } else {
                    // If there's an odd number of hashes, keep the current hash as it is
                    newHashes.add(hashes.get(i));
                }
            }
            hashes = newHashes;  // Update to the new level of hashes
        }

        // Return the final root hash
        return hashes.get(0);
    }

    // Get the verification path for a specific data block at a given index
    public static List<String> getVerificationPath(List<String> dataBlocks, int index) {
        List<String> path = new ArrayList<>();
        List<String> hashes = new ArrayList<>();

        // Compute the hash for each data block
        for (String dataBlock : dataBlocks) {
            hashes.add(hash(dataBlock));
        }

        // Retrieve the path for verification
        while (hashes.size() > 1) {
            List<String> newHashes = new ArrayList<>();
            for (int i = 0; i < hashes.size(); i += 2) {
                if (i + 1 < hashes.size()) {
                    // If there are two adjacent nodes, combine and hash them
                    newHashes.add(hash(hashes.get(i) + hashes.get(i + 1)));
                    if (i == index || i + 1 == index) {
                        // Add the corresponding hash to the verification path
                        path.add(hashes.get(i + 1));  // Add the sibling hash to the path
                    }
                } else {
                    // If there's an odd number of hashes, keep the current hash as it is
                    newHashes.add(hashes.get(i));
                }
            }
            hashes = newHashes;  // Update to the new level of hashes
        }

        return path;  // Return the full verification path
    }
}
