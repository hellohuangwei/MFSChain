package com.example.mfschain.consensus;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                // 主键ID

    @Column(name = "maritime_data_id", nullable = false)
    private String maritimeDataId;   // 海事数据ID，投票的目标海事数据

    @Column(name = "node_id", nullable = false)
    private String nodeId;           // 投票的节点ID

    @Column(name = "current_block", nullable = false)
    private String currentBlock;     // 当前区块

    @Column(name = "merkle_root", nullable = false)
    private String merkleRoot;       // 默克尔树根哈希，用于验证该投票的有效性

    @Column(name = "passed_pomst", nullable = false)
    private boolean passedPoMST;     // 节点是否通过PoMST验证

    @Column(name = "vote", nullable = false)
    private int vote;                // 节点投票值，表示该节点的投票结果

    @Column(name = "trust_value", nullable = false)
    private double trustValue;       // 信任值，用于表示节点的信任度

    // 使用 @ElementCollection 映射邻居节点
    @ElementCollection
    @Column(name = "neighbor_node_id")
    private Set<String> neighbors = new HashSet<>();  // 邻居节点的集合

    // 默认构造函数（JPA要求）
    public Vote() {}

    // 带参数的构造函数
    public Vote(String maritimeDataId, String nodeId, String currentBlock, String merkleRoot, boolean passedPoMST,
                int vote, double trustValue, Set<String> neighbors) {
        this.maritimeDataId = maritimeDataId;
        this.nodeId = nodeId;
        this.currentBlock = currentBlock;
        this.merkleRoot = merkleRoot;
        this.passedPoMST = passedPoMST;
        this.vote = vote;
        this.trustValue = trustValue;
        this.neighbors = neighbors;
    }

    // Getters 和 Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaritimeDataId() {
        return maritimeDataId;
    }

    public void setMaritimeDataId(String maritimeDataId) {
        this.maritimeDataId = maritimeDataId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(String currentBlock) {
        this.currentBlock = currentBlock;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public boolean isPassedPoMST() {
        return passedPoMST;
    }

    public void setPassedPoMST(boolean passedPoMST) {
        this.passedPoMST = passedPoMST;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public double getTrustValue() {
        return trustValue;
    }

    public void setTrustValue(double trustValue) {
        this.trustValue = trustValue;
    }

    public Set<String> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Set<String> neighbors) {
        this.neighbors = neighbors;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", maritimeDataId='" + maritimeDataId + '\'' +
                ", nodeId='" + nodeId + '\'' +
                ", currentBlock='" + currentBlock + '\'' +
                ", merkleRoot='" + merkleRoot + '\'' +
                ", passedPoMST=" + passedPoMST +
                ", vote=" + vote +
                ", trustValue=" + trustValue +
                ", neighbors=" + neighbors +
                '}';
    }
}
