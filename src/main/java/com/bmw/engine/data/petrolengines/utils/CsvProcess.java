package com.bmw.engine.data.petrolengines.utils;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.util.List;

public class CsvProcess {

    static List<String[]> readData(CSVReader csvReader, ReadMode mode) throws IOException {
        if (ReadMode.ALL.equals(mode)) {
            return csvReader.readAll();
        } else {
            List<String[]> list = new java.util.ArrayList<>();
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                list.add(line);
            }
            return list;
        }
    }

    public enum ReadMode{
        ALL, ONE_BY_ONE
    }


}
