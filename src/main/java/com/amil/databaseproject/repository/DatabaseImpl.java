package com.amil.databaseproject.repository;

import com.amil.databaseproject.util.CsvHelper;
import com.amil.databaseproject.util.SequenceGenerator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseImpl implements Database {
    @Override
    public int insert(String tableName, List<String> values) {
        int nextId = SequenceGenerator.getNextId(tableName);
        CsvHelper.createCsvRecords(values, tableName, nextId);
        return nextId;
    }

    @Override
    public void update(String tableName, int rowId, List<String> values) {
        CsvHelper.updateCsvContent(tableName, rowId, values);
    }

    @Override
    public List<String> select(String tableName, int rowId) {
        return CsvHelper.getCsvContent(tableName, rowId);
    }
}
