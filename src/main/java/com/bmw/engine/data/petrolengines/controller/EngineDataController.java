package com.bmw.engine.data.petrolengines.controller;

import com.bmw.engine.data.petrolengines.model.EngineData;
import com.bmw.engine.data.petrolengines.service.EngineDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class EngineDataController {

    final private EngineDomain domain;

    @Autowired
    public EngineDataController(EngineDomain domain){
        this.domain = domain;
    }

    @GetMapping("/api/engines/{id}")
    public EngineData getOne(@PathVariable(name="id")Long id) {
        return domain.get(id);
    }

    @GetMapping("/api/engines")
    public Collection<EngineData> allEngines() {
        return domain.allEngines();
    }

    @PostMapping("/api/engines/search")
    public Collection<EngineData> search(EngineData data){
        return domain.search(data);
    }

}
