package com.example.mfschain.consensus;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    @Autowired
    private NodeVoteService nodeVoteService;

    /**
     * Create a new vote.
     * @param vote The vote object to be created
     * @return The created vote object
     */
    @PostMapping
    public Vote createVote(@RequestBody Vote vote) {
        return nodeVoteService.saveVote(vote);  // Save the vote
    }

    /**
     * Retrieve a vote by maritime data ID and node ID.
     * @param maritimeDataId The maritime data ID
     * @param nodeId The node ID
     * @return The vote found, if any
     */
    @GetMapping("/{maritimeDataId}/{nodeId}")
    public Optional<Vote> getVote(@PathVariable String maritimeDataId, @PathVariable String nodeId) {
        return nodeVoteService.getVoteByMaritimeDataIdAndNodeId(maritimeDataId, nodeId);  // Get the vote
    }

    /**
     * Update an existing vote.
     * @param voteId The vote ID to update
     * @param vote The updated vote object
     * @return The updated vote object
     */
    @PutMapping("/{voteId}")
    public Vote updateVote(@PathVariable Long voteId, @RequestBody Vote vote) {
        return nodeVoteService.updateVote(voteId, vote);  // Update the vote
    }

    /**
     * Delete a vote by its ID.
     * @param voteId The vote ID to delete
     */
    @DeleteMapping("/{voteId}")
    public void deleteVote(@PathVariable Long voteId) {
        nodeVoteService.deleteVote(voteId);  // Delete the vote
    }

    /**
     * Retrieve all votes by maritime data ID.
     * @param maritimeDataId The maritime data ID to search for
     * @return A set of votes for the given maritime data ID
     */
    @GetMapping("/{maritimeDataId}")
    public Set<Vote> getVotes(@PathVariable String maritimeDataId) {
        return nodeVoteService.getVotesByMaritimeDataId(maritimeDataId);  // Get all votes for the given maritime data ID
    }
}
