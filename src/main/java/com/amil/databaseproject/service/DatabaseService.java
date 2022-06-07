package com.amil.databaseproject.service;

import com.amil.databaseproject.repository.Database;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {

    private final Database database;

    public DatabaseService(Database database) {
        this.database = database;
    }

    public int insert(String tableName, List<String> values) {
        return database.insert(tableName, values);
    }

    public List<String> select(String tableName, int id) {
        return database.select(tableName, id);
    }

    public void update(String tableName, int id, List<String> values) {
        database.update(tableName, id, values);
    }
}
