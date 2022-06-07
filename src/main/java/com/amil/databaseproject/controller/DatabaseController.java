package com.amil.databaseproject.controller;

import com.amil.databaseproject.service.DatabaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/database")
public class DatabaseController {

    private final DatabaseService databaseService;

    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @PostMapping("/{tableName}/data")
    public int insert(@PathVariable String tableName, @RequestBody List<String> values) {
        return databaseService.insert(tableName, values);
    }

    @GetMapping("/{tableName}/data/{id}")
    public List<String> insert(@PathVariable String tableName, @PathVariable int id) {
        return databaseService.select(tableName, id);
    }

    @PutMapping("/{tableName}/data/{id}")
    public void insert(@PathVariable String tableName, @PathVariable int id, @RequestBody List<String> values) {
        databaseService.update(tableName, id, values);
    }
}
