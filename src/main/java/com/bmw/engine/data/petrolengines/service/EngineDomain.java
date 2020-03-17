package com.bmw.engine.data.petrolengines.service;

import com.bmw.engine.data.petrolengines.model.EngineData;

import java.util.Collection;
import java.util.List;

public interface EngineDomain {

    EngineData get(Long id);

    Collection<EngineData> allEngines();

    Collection<EngineData> search(EngineData data);

    Collection<EngineData> persist(List<EngineData> engines);

    void delete(Long id);
}
