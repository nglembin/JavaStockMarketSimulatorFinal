# 💹 StockMarketSimulatorFinal

**Symulator Giełdy** – konsolowa aplikacja w Javie, która pozwala użytkownikowi kupować i sprzedawać aktywa (akcje, obligacje), zarządzać portfelem oraz obserwować zmieniające się ceny rynkowe.

## 🚀 Funkcje

- 📥 Wczytywanie aktywów z pliku `assets.csv`
- 💾 Zapis i odczyt portfela użytkownika do/z pliku `portfolio.json`
- 💱 Kupno i sprzedaż aktywów z uwzględnieniem stanu gotówki
- 📊 Dynamiczna aktualizacja cen (losowa symulacja zmian rynkowych)
- 🖥️ Prosty interfejs tekstowy (Console UI)

## 🧠 Technologie

- Java 17 (wielomodułowy projekt Maven)
- Biblioteki zewnętrzne:
  - `Jackson` – serializacja/deserializacja JSON
  - `OpenCSV` – odczyt danych z pliku CSV

## 📁 Struktura modułów

StockMarketSimulatorFinal/
├── model/ # Klasy: Asset, Stock, Bond
├── market/ # Logika rynku i aktualizacji cen
├── portfolio/ # Portfel użytkownika i pozycje
├── persistence/ # Odczyt/zapis CSV i JSON
├── exception/ # Własne wyjątki (np. brak środków)
├── ui/ # ConsoleUI – prosty interfejs użytkownika
├── main/ # Punkt wejścia: StockMarketApp

## 📦 Wymagania

- IntelliJ IDEA (najlepiej wersja z obsługą Maven/Java 17)
- Java 17+
- Maven

## 🔧 Jak uruchomić

1. Sklonuj repozytorium:
   ```bash
   git clone https://github.com/TwojNick/StockMarketSimulatorFinal.git
   cd StockMarketSimulatorFinal
Otwórz w IntelliJ IDEA jako projekt Maven (automatycznie wykryje moduły)

Sprawdź pliki danych:
assets.csv – z danymi aktywów (np. BTC, ETH, OBL)
portfolio.json – opcjonalny, początkowy portfel (możesz usunąć)

Uruchom klasę StockMarketApp z modułu main

🧪 Przykładowe dane assets.csv
symbol,name,type,price
BTC,Bitcoin,STOCK,91000
ETH,Ethereum,STOCK,1600
OBL,Obligacja Skarbowa 10Y,BOND,1000

⚙️ Dostępne opcje w menu

== SYMULATOR GIEŁDY ==

1. Wyświetl portfel
2. Kup aktywo
3. Sprzedaj aktywo
4. Wyświetl dostępne aktywa
5. Aktualizuj ceny
6. Zapisz portfel
0. Wyjdź

🧠 Projekt edukacyjny stworzony przeze mnie w ramach nauki programowania obiektowego i Javy.
