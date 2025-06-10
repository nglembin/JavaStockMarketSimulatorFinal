package com.stockmarket.portfolio;

import com.stockmarket.model.Asset;

public class PortfolioPosition {
    private Asset asset;
    private int quantity;

    // ⚠️ Wymagany przez Jacksona
    public PortfolioPosition() {
    }

    public PortfolioPosition(Asset asset, int quantity) {
        this.asset = asset;
        this.quantity = quantity;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
