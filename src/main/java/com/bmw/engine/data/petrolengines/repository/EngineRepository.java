package com.bmw.engine.data.petrolengines.repository;

import com.bmw.engine.data.petrolengines.repository.dbmodel.PetrolEngine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRepository extends JpaRepository<PetrolEngine,Long>, EngineRepoCustom {

}

