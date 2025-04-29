package com.example.mfschain.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MaritimeDataBlockRepository extends JpaRepository<MaritimeBlockData, Long> {
    // Custom database query methods can be added here if necessary

//     Custom query method to find by rootHash
    MaritimeBlockData findByRootHash(String rootHash);

    // Custom query method to find by vesselId
    List<MaritimeBlockData> findByVesselId(String vesselId);

}
