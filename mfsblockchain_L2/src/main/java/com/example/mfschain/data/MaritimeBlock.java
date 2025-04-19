package com.example.mfschain.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.MessageDigest;
import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
@Table(name = "maritime_blocks")
public class MaritimeBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Block height */
    @Column(name = "height")  // Using backticks to wrap field names
    private int height;

    /** Timestamp of when the block was created (in milliseconds) */
    @Column(name = "timestamp")
    private long timestamp;

    /** Maritime data or Merkle root */
    @Column(name = "root_hash")
    private String rootHash;

    /** Hash of the previous block */
    @Column(name = "previous_hash")
    private String previousHash;

    /** Hash of the current block */
    @Column(name = "hash")
    private String hash;

    // Constructor
    public MaritimeBlock(int index, String rootHash, String previousHash) {
        this.height = index;
        this.timestamp = Instant.now().toEpochMilli();
        this.rootHash = rootHash;
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }

    // Method to calculate the hash of the block
    public String calculateHash() {
        try {
            String text = height + timestamp + rootHash + previousHash;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(text.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));  // Convert byte to hexadecimal
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error while calculating maritime block hash", e);
        }
    }
}
