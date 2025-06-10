package com.stockmarket.persistence;

import com.stockmarket.portfolio.Portfolio;

import java.util.Optional;

/**
 * Serwis zarządzający logiką zapisu i odczytu portfela.
 */
public class PortfolioService {

    private final PortfolioRepository repository;

    public PortfolioService(PortfolioRepository repository) {
        this.repository = repository;
    }

    /**
     * Zapisuje podany portfel do repozytorium.
     *
     * @param portfolio portfel do zapisania
     */
    public void savePortfolio(Portfolio portfolio) {
        repository.save(portfolio);
    }

    /**
     * Wczytuje portfel z repozytorium.
     *
     * @return Optional zawierający portfel (lub pusty, jeśli nie uda się wczytać)
     */
    public Optional<Portfolio> loadPortfolio() {
        return repository.load();
    }
}
