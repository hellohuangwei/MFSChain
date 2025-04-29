package com.example.mfschain.data;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaritimeData {

    /** vessel ID */
    private String vesselId;

    /** Position */
    private String position;

    /** Speed */
    private String speed;

    /** Timestamp */
    private String timestamp;

    /** Hash of the current block */
    private String hash;

    // Constructor
    public MaritimeData(String vesselId, String position, String speed, String timestamp) {
        this.vesselId = vesselId;
        this.position = position;
        this.speed = speed;
        this.timestamp = timestamp;
    }
}
