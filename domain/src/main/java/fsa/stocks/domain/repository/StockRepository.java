package fsa.stocks.domain.repository;

import fsa.stocks.domain.Stock;
import fsa.stocks.domain.enums.StockSymbol;

import java.util.List;
import java.util.Optional;

public interface StockRepository {
    Optional<Stock> findBySymbol(StockSymbol symbol);
    List<Stock> readAll();
    void create(Stock stock);
}