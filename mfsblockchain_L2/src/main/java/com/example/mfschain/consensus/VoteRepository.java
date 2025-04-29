package com.example.mfschain.consensus;

import com.example.mfschain.data.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface VoteRepository extends BaseRepository<Vote, Long> {

    /**
     * Find a vote by maritime data ID and node ID.
     * @param maritimeDataId The maritime data ID
     * @param nodeId The node ID
     * @return An Optional containing the found vote, or empty if not found
     */
    Optional<Vote> findByMaritimeDataIdAndNodeId(String maritimeDataId, String nodeId);

    /**
     * Find all votes by maritime data ID.
     * @param maritimeDataId The maritime data ID
     * @return A set of votes associated with the maritime data ID
     */
    Set<Vote> findByMaritimeDataId(String maritimeDataId);
}
