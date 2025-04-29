package com.example.mfschain.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "maritime_nodes")
public class MaritimeNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Node URL - Unique URL of the node */
    @Column(name = "node_url", nullable = false, unique = true)
    private String nodeUrl;

    /** Chain ID - Identifies which blockchain this node belongs to */
    @Column(name = "chain_id", nullable = false)
    private String chainId;

    /** Status - The current status of the node (e.g., ACTIVE, INACTIVE) */
    @Column(name = "status", nullable = false)
    private String status;

    /** Registration Time - Timestamp when the node joined the blockchain network */
    @Column(name = "registration_time", nullable = false)
    private long registrationTime;

    /** Is Verified - Whether the node is verified (e.g., through a consensus or administrator) */
    @Column(name = "is_verified", nullable = false)
    private boolean isVerified;

    /** Can Be Searched - Whether the node can be queried by other nodes (e.g., to discover information) */
    @Column(name = "can_be_searched", nullable = false)
    private boolean canBeSearched;

    /** Is Storage Node - Whether the node stores the blockchain data */
    @Column(name = "is_storage_node", nullable = false)
    private boolean isStorageNode;

    /** Is Retrieval Node - Whether the node retrieves blockchain data */
    @Column(name = "is_retrieval_node", nullable = false)
    private boolean isRetrievalNode;

    /** Is Validation Node - Whether the node validates blockchain data */
    @Column(name = "is_validation_node", nullable = false)
    private boolean isValidationNode;

    @Version
    private Integer version;

    // Constructor with all fields
    public MaritimeNode(String nodeUrl, String chainId, String status, long registrationTime,
                        boolean isVerified, boolean canBeSearched, boolean isStorageNode,
                        boolean isRetrievalNode, boolean isValidationNode) {
        this.nodeUrl = nodeUrl;
        this.chainId = chainId;
        this.status = status;
        this.registrationTime = registrationTime;
        this.isVerified = isVerified;
        this.canBeSearched = canBeSearched;
        this.isStorageNode = isStorageNode;
        this.isRetrievalNode = isRetrievalNode;
        this.isValidationNode = isValidationNode;
    }
}
