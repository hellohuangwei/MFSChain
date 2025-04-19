package com.example.mfschain.data;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


public class BaseSupportRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

   private EntityManager entityManager;
   private JpaEntityInformation<T, ?> entityInformation;

   public BaseSupportRepository(Class<T> domainClass, EntityManager entityManager) {
       super(domainClass, entityManager);
       this.entityManager = entityManager;
   }

   public BaseSupportRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager em) {
       super(entityInformation, em);
       this.entityInformation = entityInformation;
       this.entityManager = em;
   }


}
