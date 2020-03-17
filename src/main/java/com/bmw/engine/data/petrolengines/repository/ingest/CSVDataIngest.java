package com.bmw.engine.data.petrolengines.repository.ingest;

import com.bmw.engine.data.petrolengines.repository.EngineRepository;
import com.bmw.engine.data.petrolengines.repository.dbmodel.PetrolEngine;
import com.bmw.engine.data.petrolengines.utils.CsvDataStream;
import com.bmw.engine.data.petrolengines.utils.CsvProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Component
@Slf4j
public class CSVDataIngest {

    final private EngineRepository repository;

    @Autowired
    public CSVDataIngest(EngineRepository repository){
        this.repository = repository;
    }

    @Async
    public Optional<AsyncResult<List>> ingestData(){

        try {
            Thread.sleep(5000);
            return Optional.of(new AsyncResult<>(ingest(fileStream())));
        } catch (Exception e) {
            log.error("Could not ingest data " ,e);
        }
        return Optional.empty();

    }

    private List ingest(InputStream inputStream){
        log.info("***************** Staring the Data Ingest process ****************");
        List<String[]> data =  CsvDataStream.readData(new InputStreamReader(inputStream),1, CsvProcess.ReadMode.ALL);
        List<PetrolEngine> engines = dataModel(data);
        return repository.saveAll(engines);

    }

    private List<PetrolEngine> dataModel(List<String[]> values){
        List<PetrolEngine> data = new ArrayList<>();
        for (String[] row : values){
            PetrolEngine engine = new PetrolEngine();

            engine.setLayout(row[0]);
            engine.setEngineCode(row[1]);
            engine.setEngineYears(row[2]);
            engine.setDisplacement(row[3]);
            data.add(engine);
        }
        return data;
    }

    private InputStream fileStream() throws IOException {
        String file = "data/bmw_petrol_engines.csv";
        return Objects.requireNonNull(getClass().getClassLoader().getResource(file)).openStream();
    }

}
