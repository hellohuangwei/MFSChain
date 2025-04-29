package com.example.mfschain.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "maritime_verification_data")
public class MaritimeBlockData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Node ID */
    @Column(name = "vessel_id")
    private String vesselId;

    /** Merkle Root Hash */
    @Column(name = "root_hash")
    private String rootHash;

    /** Hash of the current block */
    @Column(name = "hash")
    private String hash;


    /** Verification Path */
    @ElementCollection
    @CollectionTable(name = "verification_paths", joinColumns = @JoinColumn(name = "verification_data_id"))
    @Column(name = "path")
    private List<List<String>>  verificationPaths;

    // Constructor to initialize MaritimeBlockData with nodeId, rootHash, and verificationPaths
    public MaritimeBlockData(String vesselId, String rootHash, List<String> verificationPaths) {
        this.vesselId = vesselId;
        this.rootHash = rootHash;
        this.verificationPaths = Collections.singletonList(verificationPaths);
    }
}
