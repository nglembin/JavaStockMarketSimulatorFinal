package com.stockmarket.main;

import com.stockmarket.market.Market;
import com.stockmarket.model.Asset;
import com.stockmarket.persistence.*;
import com.stockmarket.ui.ConsoleUI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class StockMarketApp {
    public static void main(String[] args) {
        try {
            // 1. Inicjalizacja repozytoriów
            AssetRepository assetRepo = new CsvAssetRepository("assets.csv");
            PortfolioRepository portfolioRepo = new JsonPortfolioRepository();

            // 2. Wczytanie aktywów z CSV
            Map<String, Asset> aktywa = assetRepo.loadAssetDefinitions();

            // 3. Utworzenie rynku
            Market rynek = new Market(new ArrayList<>(aktywa.values()));

            // 4. Utworzenie serwisu portfela
            PortfolioService service = new PortfolioService(portfolioRepo);

            // 5. Uruchomienie UI
            ConsoleUI ui = new ConsoleUI(rynek, service);
            ui.start();

        } catch (IOException e) {
            System.err.println("Błąd IO: " + e.getMessage());
        }
    }
}
