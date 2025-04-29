package com.example.mfschain.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MaritimeNodeRepository extends JpaRepository<MaritimeNode, Long>, JpaSpecificationExecutor<MaritimeNode> {
    // Query to find all validation nodes that are verified
    List<MaritimeNode> findByIsValidationNodeTrueAndIsVerifiedTrueAndStatus(String status);
}
