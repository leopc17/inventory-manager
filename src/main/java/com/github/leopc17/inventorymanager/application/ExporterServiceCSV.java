package com.github.leopc17.inventorymanager.application;

import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class ExporterServiceCSV {
    public static boolean generateCSV(List<String> datas) {
        try (FileWriter writer = new FileWriter("data.csv")) {
            for (String data : datas) {
                writer.append(data).append("\n");
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
            return false;
        } catch (RuntimeException e) {
            System.err.println("Unexpected error: " + e.getMessage());
            return false;
        }
    }
}