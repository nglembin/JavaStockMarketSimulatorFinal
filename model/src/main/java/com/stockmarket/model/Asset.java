package com.stockmarket.model;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type" // klucz w JSONie, np. "type": "STOCK"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = com.stockmarket.model.Stock.class, name = "STOCK"),
        @JsonSubTypes.Type(value = com.stockmarket.model.Bond.class, name = "BOND")
})
/**
 * Klasa bazowa dla aktywów (akcje, obligacje).
 */
public abstract class Asset {
    protected String symbol;
    protected String name;
    protected double currentPrice;

    // 🔧 Domyślny konstruktor – wymagany do deserializacji przez Jacksona
    public Asset() {
    }

    // Konstruktor główny
    public Asset(String symbol, String name, double currentPrice) {
        this.symbol = symbol;
        this.name = name;
        this.currentPrice = currentPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public abstract void updatePrice();
}
