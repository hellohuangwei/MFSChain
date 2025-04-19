package com.example.mfschain.data;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

//@NoRepositoryBean
@Repository
public interface MaritimeNodeRepository extends BaseRepository<MaritimeNode, Integer> {

}