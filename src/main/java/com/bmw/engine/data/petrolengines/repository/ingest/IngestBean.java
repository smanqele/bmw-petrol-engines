package com.bmw.engine.data.petrolengines.repository.ingest;

import lombok.Getter;

import java.util.List;

@Getter
public class IngestBean {

    final private List result;

    public IngestBean(List result){
        this.result = result;
    }

}
