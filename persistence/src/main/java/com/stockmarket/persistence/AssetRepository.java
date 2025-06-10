package com.stockmarket.persistence;

import com.stockmarket.model.Asset;

import java.io.IOException;
import java.util.Map;

/**
 * Interfejs repozytorium aktywów – definiuje sposób ładowania aktywów z pliku.
 */
public interface AssetRepository {

    /**
     * Ładuje definicje aktywów i zwraca mapę symbol → Asset.
     *
     * @return mapa aktywów z kluczem jako symbol (np. "BTC")
     * @throws IOException jeśli wystąpi problem z plikiem lub jego formatem
     */
    Map<String, Asset> loadAssetDefinitions() throws IOException;
}
