package com.amil.databaseproject.util;

import java.util.HashMap;
import java.util.Map;

public class SequenceGenerator {

    private static final Map<String, Integer> sequences = new HashMap<>();

    private SequenceGenerator() {
        throw new AssertionError("This is an utility class, it shouldn't be instantiated");
    }

    public static int getNextId(String tableName) {
        int id = sequences.getOrDefault(tableName, 1);

        sequences.put(tableName, id + 1);
        return id;
    }
}
