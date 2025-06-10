package com.stockmarket.market;

import com.stockmarket.model.Asset;

import java.util.*;

// klasa Market – przechowuje dostępne aktywa na rynku i aktualizuje ich ceny
public class Market {
    // mapa: symbol → obiekt Asset (np. "BTC" → Stock)
    private Map<String, Asset> assetMap;

    // Konstruktor przyjmujący listę aktywów
    public Market(List<Asset> assets) {
        this.assetMap = new HashMap<>();
        for (Asset a : assets) {
            assetMap.put(a.getSymbol(), a);
        }
    }

    // 🔥 NOWY konstruktor przyjmujący gotową mapę aktywów (np. z CSV)
    public Market(Map<String, Asset> assetMap) {
        this.assetMap = new HashMap<>(assetMap); // kopia oryginalnej mapy
    }

    // Zwraca Optional z aktywem, jeśli istnieje
    public Optional<Asset> getAsset(String symbol) {
        return Optional.ofNullable(assetMap.get(symbol));
    }

    // Aktualizuje ceny wszystkich aktywów
    public void updatePrices() {
        for (Asset a : assetMap.values()) {
            a.updatePrice();
        }
    }

    // Zwraca widok mapy aktywów
    public Map<String, Asset> getAllAssets() {
        return Collections.unmodifiableMap(assetMap);
    }
}
