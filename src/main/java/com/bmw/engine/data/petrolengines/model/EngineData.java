package com.bmw.engine.data.petrolengines.model;

import com.bmw.engine.data.petrolengines.repository.dbmodel.PetrolEngine;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EngineData {

    private PetrolEngine.Layout layout;

    private String engineCode;

    private String engineYears;

    private String displacement;



}
