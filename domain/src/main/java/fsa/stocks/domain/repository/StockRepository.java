package fsa.stocks.domain.repository;

import fsa.stocks.domain.Stock;
import fsa.stocks.domain.enums.StockSymbol;

import java.util.Collection;

public interface StockRepository {
    Stock findBySymbol(StockSymbol symbol);
    Collection<Stock> readAll();
    void create(Stock stock);
}