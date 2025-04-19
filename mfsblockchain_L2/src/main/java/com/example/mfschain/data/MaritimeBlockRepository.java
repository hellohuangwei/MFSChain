package com.example.mfschain.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaritimeBlockRepository extends JpaRepository<MaritimeBlock, Long> {
    MaritimeBlock findTopByOrderByHeightDesc();

    // 使用 @Query 注解编写自定义查询
    @Query("SELECT m FROM MaritimeBlock m WHERE m.height BETWEEN :startIndex AND :endIndex")
    List<MaritimeBlock> findBlocksInRange(int startIndex, int endIndex);
}
