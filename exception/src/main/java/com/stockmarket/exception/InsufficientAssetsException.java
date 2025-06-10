package com.stockmarket.exception;

/**
 * Wyjątek rzucany, gdy próbujemy sprzedać więcej aktywów
 * niż posiadamy w portfelu.
 */
public class InsufficientAssetsException extends Exception {

    private final String symbol;
    private final int available;
    private final int requested;

    /**
     * Konstruktor wyjątku InsufficientAssetsException.
     *
     * @param symbol Symbol aktywa
     * @param available Dostępna liczba jednostek
     * @param requested Żądana liczba jednostek do sprzedaży
     */
    public InsufficientAssetsException(String symbol, int available, int requested) {
        super("Nie wystarczająca liczba aktywów '" + symbol +
                "'. Posiadane: " + available + ", żądane: " + requested);
        this.symbol = symbol;
        this.available = available;
        this.requested = requested;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getAvailable() {
        return available;
    }

    public int getRequested() {
        return requested;
    }
}
