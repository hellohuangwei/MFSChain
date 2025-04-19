package com.example.mfschain.core;

import com.example.mfschain.data.MaritimeDataBlock;
import com.example.mfschain.data.MaritimeDataBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service

public  class MaritimeDataBlockService    {

    public void saveMaritimeDataBlock(MaritimeDataBlock dataBlock) {
        System.out.println("MaritimeDataBlockService.saveMaritimeDataBlock");
        maritimeDataBlockRepository.save(dataBlock); // Save or update the data block
    }

    public MaritimeDataBlock getMaritimeDataBlockById(Long id) {
        return maritimeDataBlockRepository.findById(id).orElse(null); // Get by ID
    }

    public List<MaritimeDataBlock> getMaritimeDataBlocksByVesselId(String vesselId) {
        return maritimeDataBlockRepository.findByVesselId(vesselId); // Custom query using vesselId
    }


    private final static  MaritimeDataBlockRepository maritimeDataBlockRepository = new MaritimeDataBlockRepository() {
        @Override
        public MaritimeDataBlock findByRootHash(String rootHash) {
            return null;
        }

        @Override
        public List<MaritimeDataBlock> findByVesselId(String vesselId) {
            return List.of();
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends MaritimeDataBlock> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends MaritimeDataBlock> List<S> saveAllAndFlush(Iterable<S> entities) {
            return List.of();
        }

        @Override
        public void deleteAllInBatch(Iterable<MaritimeDataBlock> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Long> longs) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public MaritimeDataBlock getOne(Long aLong) {
            return null;
        }

        @Override
        public MaritimeDataBlock getById(Long aLong) {
            return null;
        }

        @Override
        public MaritimeDataBlock getReferenceById(Long aLong) {
            return null;
        }

        @Override
        public <S extends MaritimeDataBlock> List<S> findAll(Example<S> example) {
            return List.of();
        }

        @Override
        public <S extends MaritimeDataBlock> List<S> findAll(Example<S> example, Sort sort) {
            return List.of();
        }

        @Override
        public <S extends MaritimeDataBlock> List<S> saveAll(Iterable<S> entities) {
            return List.of();
        }

        @Override
        public List<MaritimeDataBlock> findAll() {
            return List.of();
        }

        @Override
        public List<MaritimeDataBlock> findAllById(Iterable<Long> longs) {
            return List.of();
        }

        @Override
        public <S extends MaritimeDataBlock> S save(S entity) {
            return null;
        }

        @Override
        public Optional<MaritimeDataBlock> findById(Long aLong) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(MaritimeDataBlock entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Long> longs) {

        }

        @Override
        public void deleteAll(Iterable<? extends MaritimeDataBlock> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public List<MaritimeDataBlock> findAll(Sort sort) {
            return List.of();
        }

        @Override
        public Page<MaritimeDataBlock> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends MaritimeDataBlock> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends MaritimeDataBlock> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends MaritimeDataBlock> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends MaritimeDataBlock> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends MaritimeDataBlock, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }
    };


}
