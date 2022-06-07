package com.amil.databaseproject.util;

import com.amil.databaseproject.exception.NotFoundException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public final class CsvHelper {

    private CsvHelper() {
        throw new AssertionError("This is an utility class, it shouldn't be instantiated");
    }

    public static void createCsvRecords(List<String> values, String filename, int recordId) {
        CSVFormat format = CSVFormat.Builder.create().build();
        String outputFile = filename + ".csv";
        try (BufferedWriter fileWriter = Files.newBufferedWriter(Paths.get(outputFile), StandardOpenOption.APPEND,
                StandardOpenOption.CREATE);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, format)
        ) {
            values.add(0, "" + recordId);
            csvPrinter.printRecord(values);
            csvPrinter.flush();
            fileWriter.flush();
        } catch (IOException ex) {
            throw new RuntimeException("Failed to import data to CSV file: ", ex);
        }
    }

    public static List<String> getCsvContent(String filename, int id) {
        try (Reader in = new FileReader(filename + ".csv")) {
            return CSVFormat.DEFAULT.parse(in).getRecords().stream().filter(
                    rec -> Integer.parseInt(rec.get(0)) == id).findFirst().map(CSVRecord::toList).orElseThrow(
                    () -> {
                        throw new NotFoundException("Row not found");
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new NotFoundException("Row not found");
    }

    public static List<CSVRecord> getAllCsvContent(String filename) {
        try (Reader in = new FileReader(filename + ".csv")) {
            return CSVFormat.DEFAULT.parse(in).getRecords();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new NotFoundException("Row not found");
    }

    public static void updateCsvContent(String filename, int id, List<String> values) {
        CSVFormat format = CSVFormat.Builder.create().build();
        String outputFile = filename + ".csv";
        try (BufferedWriter fileWriter = Files.newBufferedWriter(Paths.get(outputFile), StandardOpenOption.CREATE);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, format)
        ) {
            for (CSVRecord csvRecord : getAllCsvContent(filename)) {
                if (Integer.parseInt(csvRecord.get(0)) == id) {
                    values.add(0, "" + id);
                    csvPrinter.printRecord(values);
                } else {
                    csvPrinter.printRecord(csvRecord.toList());
                }
            }
            csvPrinter.flush();
            fileWriter.flush();
        } catch (IOException ex) {
            throw new RuntimeException("Failed to import data to CSV file: ", ex);
        }
    }
}
