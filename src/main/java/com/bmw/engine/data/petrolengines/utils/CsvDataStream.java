package com.bmw.engine.data.petrolengines.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Slf4j
public class CsvDataStream {

    public static List<String[]> readData(Reader reader, int skipLines, CsvProcess.ReadMode mode) {
        CSVReader csvReader = reader(parserWithSeparator(), reader, skipLines);
        List<String[]> list = null;
        try {
            list = CsvProcess.readData(csvReader,mode);
            close(reader, csvReader);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return list;
    }


    private static void close(Reader reader, CSVReader csvReader) throws IOException {
        reader.close();
        csvReader.close();
    }

    private static CSVParser parserWithSeparator(){
        char separator = ',';
        return new CSVParserBuilder()
                .withSeparator(separator)
                .withIgnoreQuotations(true)
                .build();
    }

    private static CSVReader reader(CSVParser parser, Reader reader, int skipLines){

        return new CSVReaderBuilder(reader)
                .withSkipLines(skipLines)
                .withCSVParser(parser)
                .build();
    }


}
