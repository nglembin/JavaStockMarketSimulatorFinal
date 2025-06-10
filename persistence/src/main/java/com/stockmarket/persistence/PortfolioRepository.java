package com.stockmarket.persistence;

import com.stockmarket.portfolio.Portfolio;

import java.util.Optional;

/**
 * Interfejs repozytorium portfela – definiuje operacje zapisu i odczytu stanu portfela.
 */
public interface PortfolioRepository {

    /**
     * Zapisuje stan portfela do pliku.
     *
     * @param portfolio obiekt portfela do zapisania
     */
    void save(Portfolio portfolio);

    /**
     * Wczytuje stan portfela z pliku, jeśli istnieje.
     *
     * @return Optional z portfelem, jeśli uda się wczytać
     */
    Optional<Portfolio> load();
}
