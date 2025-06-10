package com.stockmarket.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockmarket.portfolio.Portfolio;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

// Klasa zapisująca i wczytująca portfel z pliku JSON
public class JsonPortfolioRepository implements PortfolioRepository {

    private final File file = new File("portfolio.json"); // ścieżka pliku
    private final ObjectMapper objectMapper = new ObjectMapper(); // obiekt Jacksona

    // Zapisuje portfel do pliku JSON
    @Override
    public void save(Portfolio portfolio) {
        try {
            objectMapper.writeValue(file, portfolio);
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisu portfela: " + e.getMessage());
        }
    }

    // Wczytuje portfel z pliku JSON, jeśli istnieje
    @Override
    public Optional<Portfolio> load() {
        if (!file.exists()) {
            return Optional.empty(); // brak pliku – zwracamy pusty Optional
        }
        try {
            Portfolio portfolio = objectMapper.readValue(file, Portfolio.class);
            return Optional.of(portfolio);
        } catch (IOException e) {
            System.err.println("Błąd podczas wczytywania portfela: " + e.getMessage());
            return Optional.empty(); // błąd odczytu – zwracamy pusty Optional
        }
    }
}
