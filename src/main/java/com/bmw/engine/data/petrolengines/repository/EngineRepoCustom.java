package com.bmw.engine.data.petrolengines.repository;

import com.bmw.engine.data.petrolengines.model.EngineData;
import com.bmw.engine.data.petrolengines.repository.dbmodel.PetrolEngine;

import java.util.Collection;

public interface EngineRepoCustom {

    Collection<PetrolEngine> searchByData(EngineData data);

}
