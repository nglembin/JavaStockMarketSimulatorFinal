package com.stockmarket.persistence;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.stockmarket.model.Asset;
import com.stockmarket.model.Bond;
import com.stockmarket.model.Stock;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementacja AssetRepository – ładuje aktywa z pliku CSV.
 */
public class CsvAssetRepository implements AssetRepository {

    private final String csvFilePath;

    /**
     * Konstruktor bezargumentowy – używa domyślnej ścieżki "assets.csv".
     */
    public CsvAssetRepository() {
        this.csvFilePath = "assets.csv";
    }

    /**
     * Konstruktor z niestandardową ścieżką do pliku CSV.
     * @param csvFilePath np. "assets.csv"
     */
    public CsvAssetRepository(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    /**
     * Wczytuje aktywa z CSV i tworzy obiekty Stock/Bond.
     */
    @Override
    public Map<String, Asset> loadAssetDefinitions() throws IOException {
        Map<String, Asset> assets = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] line;
            boolean headerSkipped = false;

            while ((line = reader.readNext()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true; // pomijamy nagłówek
                    continue;
                }

                String symbol = line[0];
                String name = line[1];
                String type = line[2];
                double initialPrice = Double.parseDouble(line[3]);

                Asset asset;

                if (type.equalsIgnoreCase("STOCK")) {
                    asset = new Stock(symbol, name, initialPrice);
                } else if (type.equalsIgnoreCase("BOND")) {
                    // Domyślne oprocentowanie – można to rozbudować na przyszłość
                    asset = new Bond(symbol, name, initialPrice, 0.01);
                } else {
                    System.out.println("❌ Nieznany typ aktywa: " + type);
                    continue;
                }

                assets.put(symbol, asset);
            }

        } catch (CsvValidationException e) {
            throw new IOException("Błąd walidacji CSV: " + e.getMessage(), e);
        }

        return assets;
    }
}
