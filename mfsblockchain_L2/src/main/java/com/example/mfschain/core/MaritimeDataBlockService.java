package com.example.mfschain.core;

import com.example.mfschain.data.MaritimeBlockData;
import com.example.mfschain.data.MaritimeDataBlockRepository;
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

    public void saveMaritimeDataBlock(MaritimeBlockData dataBlock) {
        System.out.println("MaritimeDataBlockService.saveMaritimeDataBlock");
        maritimeDataBlockRepository.save(dataBlock); // Save or update the data block
    }

    public MaritimeBlockData getMaritimeDataBlockById(Long id) {
        return maritimeDataBlockRepository.findById(id).orElse(null); // Get by ID
    }

    public List<MaritimeBlockData> getMaritimeDataBlocksByVesselId(String vesselId) {
        return maritimeDataBlockRepository.findByVesselId(vesselId); // Custom query using vesselId
    }


    private final static  MaritimeDataBlockRepository maritimeDataBlockRepository = new MaritimeDataBlockRepository() {
        @Override
        public MaritimeBlockData findByRootHash(String rootHash) {
            return null;
        }

        @Override
        public List<MaritimeBlockData> findByVesselId(String vesselId) {
            return List.of();
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends MaritimeBlockData> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends MaritimeBlockData> List<S> saveAllAndFlush(Iterable<S> entities) {
            return List.of();
        }

        @Override
        public void deleteAllInBatch(Iterable<MaritimeBlockData> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Long> longs) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public MaritimeBlockData getOne(Long aLong) {
            return null;
        }

        @Override
        public MaritimeBlockData getById(Long aLong) {
            return null;
        }

        @Override
        public MaritimeBlockData getReferenceById(Long aLong) {
            return null;
        }

        @Override
        public <S extends MaritimeBlockData> List<S> findAll(Example<S> example) {
            return List.of();
        }

        @Override
        public <S extends MaritimeBlockData> List<S> findAll(Example<S> example, Sort sort) {
            return List.of();
        }

        @Override
        public <S extends MaritimeBlockData> List<S> saveAll(Iterable<S> entities) {
            return List.of();
        }

        @Override
        public List<MaritimeBlockData> findAll() {
            return List.of();
        }

        @Override
        public List<MaritimeBlockData> findAllById(Iterable<Long> longs) {
            return List.of();
        }

        @Override
        public <S extends MaritimeBlockData> S save(S entity) {
            return null;
        }

        @Override
        public Optional<MaritimeBlockData> findById(Long aLong) {
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
        public void delete(MaritimeBlockData entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Long> longs) {

        }

        @Override
        public void deleteAll(Iterable<? extends MaritimeBlockData> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public List<MaritimeBlockData> findAll(Sort sort) {
            return List.of();
        }

        @Override
        public Page<MaritimeBlockData> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends MaritimeBlockData> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends MaritimeBlockData> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends MaritimeBlockData> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends MaritimeBlockData> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends MaritimeBlockData, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }
    };


}
