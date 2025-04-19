package com.example.mfschain.test;

import java.util.Arrays;
import java.util.List;

public class TestMekerTree {

    // Simulate maritime data (e.g., ship position, speed, etc.)
    public static List<String> getMaritimeData() {
        return Arrays.asList(
                "{\"shipId\": \"SHIP123\", \"position\": \"LAT: 35.6895, LON: 139.6917\", \"speed\": \"25 knots\"}",
                "{\"shipId\": \"SHIP124\", \"position\": \"LAT: 37.6895, LON: 140.6917\", \"speed\": \"20 knots\"}",
                "{\"shipId\": \"SHIP125\", \"position\": \"LAT: 36.6895, LON: 138.6917\", \"speed\": \"15 knots\"}"
        );
    }

    public static void storeRootHash() {
        List<String> maritimeData = getMaritimeData();

        // Step 1: Build the Merkle tree and get the root hash
        String rootHash = MerkleTreeUtil.buildMerkleTree(maritimeData);

        // Step 2: Store the root hash in the database
        System.out.println("Root hash: " + rootHash);

        // You can store the rootHash in the database, assuming you have a database operation method
        // storeRootHashInDatabase(rootHash);
    }

    public static void verifyData(int index, String data) {
        List<String> maritimeData = getMaritimeData();

        // Step 1: Compute the hash of the data block
        String dataHash = MerkleTreeUtil.hash(data);

        // Step 2: Get the verification path
        List<String> verificationPath = MerkleTreeUtil.getVerificationPath(maritimeData, index);

        // Step 3: Verify the data by following the verification path
        String calculatedRootHash = dataHash;
        for (String hash : verificationPath) {
            calculatedRootHash = MerkleTreeUtil.hash(calculatedRootHash + hash);
        }

        // Step 4: Compare the calculated root hash with the stored root hash
        // Assume you retrieved the root hash from the database
        String storedRootHash = "root hash stored in the database";  // This can be retrieved through a database query

        if (calculatedRootHash.equals(storedRootHash)) {
            System.out.println("Data is valid.");
        } else {
            System.out.println("Data is invalid.");
        }
    }

    public static void main(String[] args) {
        storeRootHash();

        // Verify a specific piece of data
        verifyData(0, "{\"shipId\": \"SHIP123\", \"position\": \"LAT: 35.6895, LON: 139.6917\", \"speed\": \"25 knots\"}");
    }
}
