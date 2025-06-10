package com.stockmarket.exception;

/**
 * Wyjątek rzucany, gdy aktywo o podanym symbolu
 * nie istnieje na rynku lub w portfelu.
 */
public class AssetNotFoundException extends Exception {

    private final String symbol;

    /**
     * Konstruktor wyjątku AssetNotFoundException.
     *
     * @param symbol Symbol aktywa, które nie zostało znalezione
     */
    public AssetNotFoundException(String symbol) {
        super("Aktywo o symbolu '" + symbol + "' nie zostało znalezione.");
        this.symbol = symbol;
    }

    /**
     * Zwraca symbol aktywa, którego nie znaleziono.
     *
     * @return Symbol aktywa
     */
    public String getSymbol() {
        return symbol;
    }
}
