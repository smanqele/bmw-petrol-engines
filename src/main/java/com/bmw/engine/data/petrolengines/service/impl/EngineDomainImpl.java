package com.bmw.engine.data.petrolengines.service.impl;

import com.bmw.engine.data.petrolengines.model.EngineData;
import com.bmw.engine.data.petrolengines.repository.EngineRepository;
import com.bmw.engine.data.petrolengines.repository.dbmodel.PetrolEngine;
import com.bmw.engine.data.petrolengines.service.EngineDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class EngineDomainImpl implements EngineDomain{

    @Autowired
    private EngineRepository repository;

    @Override
    public EngineData get(Long id) {
        return PetrolEngine.transform(repository.getOne(id));
    }

    @Override
    public Collection<EngineData> allEngines() {
        return PetrolEngine.transform(repository.findAll());
    }

    @Override
    public Collection<EngineData> search(EngineData data) {
        return PetrolEngine.transform(repository.searchByData(data));
    }

    @Override
    public Collection<EngineData> persist(List<EngineData> engines) {
        throw new IllegalStateException("EngineDomainImpl.persist not yet implemented ");
    }

    @Override
    public void delete(Long id) {
        throw new IllegalStateException("EngineDomainImpl.delete not yet implemented ");
    }
}
