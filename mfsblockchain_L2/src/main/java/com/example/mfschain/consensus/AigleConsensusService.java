package com.example.mfschain.consensus;

import com.example.mfschain.core.MaritimeBlockService;
import com.example.mfschain.data.MaritimeBlock;
import com.example.mfschain.data.MaritimeBlockRepository;
import com.example.mfschain.data.MaritimeNode;
import com.example.mfschain.data.MaritimeNodeRepository;
import com.example.mfschain.p2p.BlockWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AigleConsensusService {

    @Autowired
    private VoteRepository voteRepository;  // Repository for vote data

    @Autowired
    private MaritimeBlockRepository maritimeBlockRepository;  // Repository for maritime blocks

    @Autowired
    private MaritimeBlockService maritimeBlockService;  // Block service for creating and broadcasting blocks

    @Autowired
    private MaritimeNodeRepository maritimeNodeRepository;

    @Autowired
    private BlockWebSocketHandler blockWebSocketHandler;  // Inject the WebSocket handler

    /**
     * Step 1: Initial Sampling
     * Retrieves vote data from the database and performs initial sampling.
     * @param maritimeDataId The maritime data ID
     * @param nodeId The current node ID
     * @return Whether the initial sampling passes
     */
    public boolean initialSampling(String maritimeDataId, String nodeId) {
        // Randomly select nodes and retrieve their vote data
        Set<String> sampledNodes = selectRandomNodes(nodeId);
        int voteCount = 0;

        // Iterate over the selected nodes and check if they pass PoMST
        for (String k : sampledNodes) {
            Optional<Vote> voteOptional = voteRepository.findByMaritimeDataIdAndNodeId(maritimeDataId, k);
            if (voteOptional.isPresent()) {
                Vote vote = voteOptional.get();
                if (vote.isPassedPoMST()) {
                    voteCount++;
                }
            }
        }

        // Calculate the initial vote total
        int Vj0 = voteCount;

        // If Vj0 reaches the threshold, proceed to secondary sampling
        if (Vj0 >= 3) {  // Assuming v_min = 3
            return true;
        }

        // Otherwise, reject the node's data
        return false;
    }

    /**
     * Step 2: Secondary Sampling and Cluster Synchronization
     * Synchronizes clusters and performs secondary sampling.
     * @param maritimeDataId The maritime data ID
     * @param nodeId The current node ID
     */
    public void secondarySampling(String maritimeDataId, String nodeId) {
        // Reset the C_j flag
        int Cj = 1;

        // Randomly select secondary sampling nodes
        Set<String> sampledNodes = selectRandomNodes(nodeId);

        // Compute clustering influence factor for each node
        for (String i : sampledNodes) {
            double impact = computeClusteringImpact(i, nodeId);
            if (impact >= 0.5) {  // Assuming theta_impact = 0.5
                synchronizeVotes(i, nodeId);
            }
        }
    }

    /**
     * Compute the clustering impact factor.
     * @param nodeA Node A
     * @param nodeB Node B
     * @return The clustering impact factor
     */
    private double computeClusteringImpact(String nodeA, String nodeB) {
        double dataInteraction = 1.0;  // Simplified value
        double communicationFreq = 1.0;  // Simplified value
        double balance = 1.0;  // Simplified value

        double alpha1 = 0.4, alpha2 = 0.3, alpha3 = 0.3;  // Assumed weights
        return alpha1 * dataInteraction + alpha2 * communicationFreq + alpha3 * balance;
    }

    /**
     * Synchronize votes for nodes in the same cluster.
     * @param nodeA Node A
     * @param nodeB Node B
     */
    private void synchronizeVotes(String nodeA, String nodeB) {
        // Synchronize vote data between nodes A and B (simplified)
        Optional<Vote> voteA = voteRepository.findByMaritimeDataIdAndNodeId(nodeA, nodeB);
        Optional<Vote> voteB = voteRepository.findByMaritimeDataIdAndNodeId(nodeB, nodeA);

        if (voteA.isPresent() && voteB.isPresent()) {
            // Sync the votes between node A and node B
            int synchronizedVote = (voteA.get().getVote() + voteB.get().getVote()) / 2;

            voteA.get().setVote(synchronizedVote);
            voteB.get().setVote(synchronizedVote);

            voteRepository.save(voteA.get());
            voteRepository.save(voteB.get());
        }
    }

    /**
     * Step 3: Global Consensus
     * Checks if global consensus is achieved.
     * @param maritimeDataId The maritime data ID
     * @return Whether global consensus is achieved
     */
    public boolean globalConsensus(String maritimeDataId) {
        List<Vote> votes = (List<Vote>) voteRepository.findByMaritimeDataId(maritimeDataId);
        int VjSum = 0;

        // Calculate the total votes
        for (Vote vote : votes) {
            VjSum += vote.isPassedPoMST() ? 1 : 0;
        }

        // Check if global consensus is reached
        if (VjSum >= 3) {  // Assuming theta_network = 3
            // Create a new block after consensus
            Optional<MaritimeBlock> lastBlock = maritimeBlockRepository.findTopByOrderByHeightDesc();
            MaritimeBlock previousBlock = lastBlock.orElseThrow(() -> new RuntimeException("No previous block found"));

            // Generate a Merkle root for the new block (simplified here as an example)
            String newRootHash = getNewBlockHash();

            // Create a new block and broadcast it
            MaritimeBlock newBlock = maritimeBlockService.createNewBlock(newRootHash, previousBlock);

            return true;
        }

        return false;
    }


    private String getNewBlockHash() {
        // In a real scenario, generate a Merkle root based on data
        return "newBlockHash";
    }


    /**
     * Randomly select nodes for sampling (from the database).
     * @param nodeId The current node ID
     * @return A set of randomly selected nodes
     */
    private Set<String> selectRandomNodes(String nodeId) {
        // Fetch all verified and validation nodes with the status 'ACTIVE'
        List<MaritimeNode> validationNodes = maritimeNodeRepository.findByIsValidationNodeTrueAndIsVerifiedTrueAndStatus("ACTIVE");

        // Exclude the current node from the list
        validationNodes = validationNodes.stream()
                .filter(node -> !node.getNodeUrl().equals(nodeId))
                .collect(Collectors.toList());

        // Set the number of nodes to sample, for example, 3 nodes.
        int numberOfSamples = 3;

        // Random object to handle randomization
        Random random = new Random();

        // Randomly select 'numberOfSamples' nodes from the remaining list
        Set<String> sampledNodes = random.ints(0, validationNodes.size())
                .distinct()
                .limit(numberOfSamples)
                .mapToObj(index -> validationNodes.get(index).getNodeUrl())
                .collect(Collectors.toSet());

        return sampledNodes;
    }

    /**
     * After consensus is achieved, create a new block and broadcast it.
     * @param newBlockHash then ,according The new block hash, other node get the main block data on the database
     */
    public void createAndBroadcastNewBlock(String newBlockHash) {
        // Logic to create a new block (e.g., using your MaritimeBlock class)
        // This part will depend on your existing block creation logic.

        // Broadcasting the new block to all connected clients
        blockWebSocketHandler.broadcastNewBlock(newBlockHash);
    }
}
