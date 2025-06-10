package com.stockmarket.exception;

/**
 * Wyjątek rzucany, gdy nie ma wystarczających środków
 * do zakupu aktywów.
 */
public class InsufficientFundsException extends Exception {

    private final double availableFunds;
    private final double requiredFunds;

    /**
     * Konstruktor wyjątku InsufficientFundsException.
     *
     * @param availableFunds Dostępna gotówka
     * @param requiredFunds Wymagana kwota do zakupu
     */
    public InsufficientFundsException(double availableFunds, double requiredFunds) {
        super("Brak wystarczających środków. Dostępne: " +
                availableFunds + " zł, wymagane: " + requiredFunds + " zł");
        this.availableFunds = availableFunds;
        this.requiredFunds = requiredFunds;
    }

    public double getAvailableFunds() {
        return availableFunds;
    }

    public double getRequiredFunds() {
        return requiredFunds;
    }
}
