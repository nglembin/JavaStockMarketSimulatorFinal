package com.stockmarket.portfolio;

import com.stockmarket.exception.AssetNotFoundException;
import com.stockmarket.exception.InsufficientAssetsException;
import com.stockmarket.exception.InsufficientFundsException;
import com.stockmarket.market.Market;
import com.stockmarket.market.Tradable;
import com.stockmarket.model.Asset;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private double cash;
    private Map<String, PortfolioPosition> positions;

    // ⚠️ Wymagany przez Jacksona
    public Portfolio() {
        this.cash = 0.0;
        this.positions = new HashMap<>();
    }

    public Portfolio(double initialCash) {
        if (initialCash < 0) {
            throw new IllegalArgumentException("Gotówka nie może być ujemna");
        }
        this.cash = initialCash;
        this.positions = new HashMap<>();
    }

    public void buy(String symbol, int quantity, Market market)
            throws InsufficientFundsException, AssetNotFoundException {

        Asset asset = market.getAsset(symbol)
                .orElseThrow(() -> new AssetNotFoundException(symbol));

        if (!(asset instanceof Tradable)) {
            throw new AssetNotFoundException(symbol);
        }

        Tradable tradable = (Tradable) asset;
        double cost = tradable.getCurrentPrice() * quantity;

        if (cash < cost) {
            throw new InsufficientFundsException(cash, cost);
        }

        cash -= cost;
        addOrUpdatePosition(asset, quantity);
    }

    public void sell(String symbol, int quantity, Market market)
            throws AssetNotFoundException, InsufficientAssetsException {

        PortfolioPosition pos = positions.get(symbol);
        if (pos == null) {
            throw new AssetNotFoundException(symbol);
        }

        int available = pos.getQuantity();
        if (quantity > available) {
            throw new InsufficientAssetsException(symbol, available, quantity);
        }

        Asset asset = market.getAsset(symbol)
                .orElseThrow(() -> new AssetNotFoundException(symbol));

        if (!(asset instanceof Tradable)) {
            throw new AssetNotFoundException(symbol);
        }

        Tradable tradable = (Tradable) asset;
        double income = tradable.getCurrentPrice() * quantity;

        cash += income;
        removeOrUpdatePosition(symbol, pos, quantity);
    }

    private void addOrUpdatePosition(Asset asset, int quantity) {
        String symbol = asset.getSymbol();
        if (positions.containsKey(symbol)) {
            PortfolioPosition existing = positions.get(symbol);
            int newQty = existing.getQuantity() + quantity;
            positions.put(symbol, new PortfolioPosition(asset, newQty));
        } else {
            positions.put(symbol, new PortfolioPosition(asset, quantity));
        }
    }

    private void removeOrUpdatePosition(String symbol, PortfolioPosition pos, int quantityToRemove) {
        int newQty = pos.getQuantity() - quantityToRemove;
        if (newQty > 0) {
            positions.put(symbol, new PortfolioPosition(pos.getAsset(), newQty));
        } else {
            positions.remove(symbol);
        }
    }

    public double calculateAssetsValue() {
        double total = 0.0;
        for (PortfolioPosition pos : positions.values()) {
            total += pos.getAsset().getCurrentPrice() * pos.getQuantity();
        }
        return total;
    }

    public double calculateTotalValue() {
        return cash + calculateAssetsValue();
    }

    // Wymagane przez Jacksona
    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public Map<String, PortfolioPosition> getPositions() {
        return positions;
    }

    public void setPositions(Map<String, PortfolioPosition> positions) {
        this.positions = positions;
    }
}
