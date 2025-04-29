package com.example.mfschain.consensus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class NodeVoteService {

    @Autowired
    private VoteRepository voteRepository;  // Injecting the VoteRepository to perform database operations

    /**
     * Save a new vote record.
     * @param vote The vote object to be saved
     * @return The saved vote object
     */
    @Transactional
    public Vote saveVote(Vote vote) {
        // Save the vote to the database
        return voteRepository.save(vote);
    }

    /**
     * Retrieve a vote by maritime data ID and node ID.
     * @param maritimeDataId The maritime data ID
     * @param nodeId The node ID
     * @return An Optional containing the found vote, or empty if not found
     */
    public Optional<Vote> getVoteByMaritimeDataIdAndNodeId(String maritimeDataId, String nodeId) {
        // Find a vote by maritime data ID and node ID
        return voteRepository.findByMaritimeDataIdAndNodeId(maritimeDataId, nodeId);
    }

    /**
     * Update an existing vote.
     * @param voteId The vote ID to update
     * @param vote The updated vote object
     * @return The updated vote object, or null if not found
     */
    @Transactional
    public Vote updateVote(Long voteId, Vote vote) {
        // Check if the vote exists before updating
        if (voteRepository.existsById(voteId)) {
            vote.setId(voteId);  // Set the ID of the updated vote
            return voteRepository.save(vote);  // Save the updated vote
        }
        return null;  // Return null if the vote ID does not exist
    }

    /**
     * Delete a vote by its ID.
     * @param voteId The vote ID to delete
     */
    @Transactional
    public void deleteVote(Long voteId) {
        // Delete the vote from the database
        voteRepository.deleteById(voteId);
    }

    /**
     * Retrieve all votes by maritime data ID.
     * @param maritimeDataId The maritime data ID to search for
     * @return A set of votes associated with the given maritime data ID
     */
    public Set<Vote> getVotesByMaritimeDataId(String maritimeDataId) {
        // Find all votes associated with a specific maritime data ID
        return voteRepository.findByMaritimeDataId(maritimeDataId);
    }
}
