package com.example.mfschain.consensus;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class AigleConsensusService {

    private final double vMin = 5;             // Minimum voting threshold
    private final double thetaImpact = 0.5;    // Impact threshold
    private final double thetaNetwork = 0.8;   // Network consensus threshold
    private final double alpha1 = 0.2;
    private final double alpha2 = 0.3;
    private final double alpha3 = 0.5;

    private final List<Node> nodes = new ArrayList<>();

    // Called after bean initialization
    @PostConstruct
    public void init() {
        initializeNodes(10); // Create 10 sample nodes
        startConsensus();    // Launch consensus process
    }

    // Initialize test nodes
    private void initializeNodes(int numberOfNodes) {
        for (int i = 0; i < numberOfNodes; i++) {
            Node node = new Node(i);
            if (i % 2 == 0) {
                node.setPassedPoMST(true); // Some nodes passed PoMST
            }
            nodes.add(node);
        }
    }

    // Start the consensus process
    public void startConsensus() {
        for (Node node : nodes) {
            initialSampling(node);
        }
    }

    // Initial sampling step
    private void initialSampling(Node node) {
        Set<Node> sampleSet = new HashSet<>(nodes);
        int initialVotes = 0;

        for (Node neighbor : sampleSet) {
            if (neighbor.isPassedPoMST()) {
                neighbor.setVote(1);
                initialVotes++;
            } else {
                neighbor.setVote(0);
            }
        }

        if (initialVotes >= vMin) {
            secondarySamplingAndClusterSync(node);
        } else {
            rejectData(node);
        }
    }

    // Secondary sampling and clustering
    private void secondarySamplingAndClusterSync(Node node) {
        Set<Node> sampleSet = new HashSet<>(nodes);
        Map<Integer, Set<Node>> clusters = new HashMap<>();
        clusters.put(node.getId(), new HashSet<>());

        for (Node other : sampleSet) {
            double impact = node.calculateImpact(other);
            if (impact >= thetaImpact) {
                clusters.get(node.getId()).add(other);
            }
        }

        synchronizeVotesAndUpdateTrust(node, clusters);
    }

    // Synchronize votes and update trust
    private void synchronizeVotesAndUpdateTrust(Node node, Map<Integer, Set<Node>> clusters) {
        for (Map.Entry<Integer, Set<Node>> cluster : clusters.entrySet()) {
            Set<Node> clusterNodes = cluster.getValue();
            int majorityVote = 0;

            for (Node clusterNode : clusterNodes) {
                majorityVote += clusterNode.getVote();
            }

            double newTrust = majorityVote >= (clusterNodes.size() / 2.0) ? 1 : 0;
            node.setTrustValue(newTrust == 1 ? node.getTrustValue() + 1 : node.getTrustValue() - 1);
        }

        checkGlobalConsensus(node);
    }

    // Check for global consensus
    private void checkGlobalConsensus(Node node) {
        double totalTrust = 0;
        for (Node n : nodes) {
            totalTrust += n.getTrustValue();
        }

        if (totalTrust >= thetaNetwork * nodes.size()) {
            acceptData(node);
        } else {
            rejectData(node);
        }
    }

    // Accept node's data
    private void acceptData(Node node) {
        log.info("Node {}'s data accepted by the network.", node.getId());
    }

    // Reject node's data
    private void rejectData(Node node) {
        log.warn("Node {}'s data rejected by the network.", node.getId());
    }

    // Internal class for node representation
    static class Node {
        private final int id;
        private boolean passedPoMST;
        private int vote;
        private double trustValue;
        private final Set<Node> neighbors = new HashSet<>();

        public Node(int id) {
            this.id = id;
        }

        public double calculateImpact(Node other) {
            double dataInteraction = 1.0;
            double commFreq = 1.0;
            double balance = 1.0;
            return 0.2 * dataInteraction + 0.3 * commFreq + 0.5 * balance;
        }

        public int getId() {
            return id;
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

        public Set<Node> getNeighbors() {
            return neighbors;
        }
    }
}
