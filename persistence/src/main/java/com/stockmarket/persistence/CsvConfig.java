package com.stockmarket.persistence;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Klasa pomocnicza do zarządzania plikiem CSV z aktywami
public class CsvConfig {

    // Nazwa pliku CSV z aktywami
    private static final String ASSETS_CSV_FILE = "assets.csv";

    // Tworzy domyślny plik CSV, jeśli nie istnieje
    public static void ensureCsvExists() {
        Path path = Paths.get(ASSETS_CSV_FILE);

        if (Files.notExists(path)) {
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                writer.write("symbol,name,type,initial_price\n");
                writer.write("BTC,Bitcoin,STOCK,91000.0\n");
                writer.write("ETH,Ethereum,STOCK,1698.0\n");
                writer.write("PMS,ProfesorMiotkSzef,STOCK,9999.99\n");
                writer.write("OBL1,Obligacja Skarbowa 10Y,BOND,1000.0\n");
                System.out.println("✅ Plik assets.csv został utworzony.");
            } catch (IOException e) {
                System.out.println("❌ Błąd podczas tworzenia pliku assets.csv: " + e.getMessage());
            }
        }
    }

    // Getter ścieżki do pliku CSV
    public static String getAssetsCsvPath() {
        return ASSETS_CSV_FILE;
    }
}
