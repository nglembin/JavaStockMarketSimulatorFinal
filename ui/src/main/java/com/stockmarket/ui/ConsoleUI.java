package com.stockmarket.ui;

import com.stockmarket.market.Market;
import com.stockmarket.portfolio.Portfolio;
import com.stockmarket.portfolio.PortfolioPosition;
import com.stockmarket.persistence.PortfolioService;
import com.stockmarket.exception.*;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleUI {

    private final Scanner scanner = new Scanner(System.in);
    private final Market market;
    private final PortfolioService portfolioService;
    private Portfolio portfolio;

    public ConsoleUI(Market market, PortfolioService portfolioService) {
        this.market = market;
        this.portfolioService = portfolioService;

        // Wczytujemy zapisany portfel albo tworzymy nowy
        Optional<Portfolio> loaded = portfolioService.loadPortfolio();
        this.portfolio = loaded.orElseGet(() -> new Portfolio(100000.0));
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== SYMULATOR GIEŁDY ===");
            System.out.println("1. Pokaż rynek");
            System.out.println("2. Pokaż portfel");
            System.out.println("3. Kup aktywo");
            System.out.println("4. Sprzedaj aktywo");
            System.out.println("5. Aktualizuj ceny (symulacja)");
            System.out.println("6. Zapisz portfel");
            System.out.println("0. Wyjdź");
            System.out.print("Wybierz opcję: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> showMarket();
                case "2" -> showPortfolio();
                case "3" -> buyAsset();
                case "4" -> sellAsset();
                case "5" -> simulatePrices();
                case "6" -> savePortfolio();
                case "0" -> {
                    savePortfolio();
                    running = false;
                }
                default -> System.out.println("Nieznana opcja!");
            }
        }
    }

    private void showMarket() {
        System.out.println("\n=== RYNEK ===");
        market.getAllAssets().forEach((symbol, asset) -> {
            System.out.printf("%s (%s): %.2f PLN\n", symbol, asset.getName(), asset.getCurrentPrice());
        });
    }

    private void showPortfolio() {
        System.out.println("\n=== TWÓJ PORTFEL ===");
        System.out.printf("Gotówka: %.2f PLN\n", portfolio.getCash());
        for (Map.Entry<String, PortfolioPosition> entry : portfolio.getPositions().entrySet()) {
            var pos = entry.getValue();
            double value = pos.getAsset().getCurrentPrice() * pos.getQuantity();
            System.out.printf("- %s (%s): %d szt. = %.2f PLN\n",
                    pos.getAsset().getSymbol(),
                    pos.getAsset().getName(),
                    pos.getQuantity(),
                    value);
        }
        System.out.printf("Wartość aktywów: %.2f PLN\n", portfolio.calculateAssetsValue());
        System.out.printf("Całkowita wartość portfela: %.2f PLN\n", portfolio.calculateTotalValue());
    }

    private void buyAsset() {
        System.out.print("Podaj symbol aktywa do kupna: ");
        String symbol = scanner.nextLine();
        System.out.print("Podaj ilość: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        try {
            portfolio.buy(symbol, quantity, market);
            System.out.println("Kupiono aktywo.");
        } catch (InsufficientFundsException | AssetNotFoundException e) {
            System.err.println("Błąd przy kupnie: " + e.getMessage());
        }
    }

    private void sellAsset() {
        System.out.print("Podaj symbol aktywa do sprzedaży: ");
        String symbol = scanner.nextLine();
        System.out.print("Podaj ilość: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        try {
            portfolio.sell(symbol, quantity, market);
            System.out.println("Sprzedano aktywo.");
        } catch (InsufficientAssetsException | AssetNotFoundException e) {
            System.err.println("Błąd przy sprzedaży: " + e.getMessage());
        }
    }

    private void simulatePrices() {
        market.updatePrices();
        System.out.println("Ceny zaktualizowane.");
    }

    private void savePortfolio() {
        portfolioService.savePortfolio(portfolio);
        System.out.println("Portfel zapisany.");
    }
}