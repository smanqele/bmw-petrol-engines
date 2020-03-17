package com.bmw.engine.data.petrolengines.config;

import com.bmw.engine.data.petrolengines.repository.ingest.CSVDataIngest;
import com.bmw.engine.data.petrolengines.repository.ingest.IngestBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;

@Configuration
@Slf4j
public class BMWDataConfig {

    final private CSVDataIngest dataIngest;

    @Autowired
    public BMWDataConfig(CSVDataIngest dataIngest){
        this.dataIngest = dataIngest;
    }

    @Bean
    public IngestBean ingest(){
        return dataIngest.ingestData().map(r -> {
            try {
                return new IngestBean(r.get());
            } catch (ExecutionException e) {
                log.error("could not ingest data ", e);
            }
            return null;
        }).orElseThrow(() -> new IllegalStateException("Could not ingest data"));
    }



}
