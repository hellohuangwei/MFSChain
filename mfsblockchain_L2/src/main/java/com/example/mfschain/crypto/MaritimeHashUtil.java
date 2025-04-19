package com.example.mfschain.crypto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MaritimeHashUtil provides SHA-256 hash calculation functionality.
 */
public class MaritimeHashUtil {

    /**
     * Computes the SHA-256 hash of the given input string.
     *
     * @param input The input string to be hashed
     * @return The hexadecimal representation of the SHA-256 hash
     */
    public static String sha256(String input) {
        try {
            // Get the MessageDigest instance for SHA-256 algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Convert the input string to byte array and compute the hash
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                // Convert each byte to a two-character hexadecimal representation
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    // If the hex string is of length 1, append a leading zero
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Throw runtime exception if the specified algorithm is not available
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }
}
