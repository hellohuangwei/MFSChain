package com.example.mfschain.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaritimeBlockRepository extends BaseRepository<MaritimeBlock, Long> {

    // 使用 @Query 注解编写自定义查询
    @Query("SELECT m FROM MaritimeBlock m WHERE m.height BETWEEN :startIndex AND :endIndex")
    List<MaritimeBlock> findBlocksInRange(int startIndex, int endIndex);

    /**
     * Finds the most recent block by height in the chain.
     * @return The most recent block.
     */
    Optional<MaritimeBlock> findTopByOrderByHeightDesc();

    /**
     * Finds a block by its hash.
     * @param hash The hash of the block.
     * @return The block that matches the hash.
     */
    Optional<MaritimeBlock> findByHash(String hash);

}
