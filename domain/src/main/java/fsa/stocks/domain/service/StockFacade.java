package fsa.stocks.domain.service;

import fsa.stocks.domain.Stock;
import fsa.stocks.domain.enums.StockSymbol;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Exposes operations for managing Stock entities.
 */
public interface StockFacade {

    /**
     * Retrieves a Stock by its symbol.
     */
    Optional<Stock> get(StockSymbol symbol);

    /**
     * Returns all available stocks in the system.
     */
    List<Stock> listAll();

    /**
     * Persists a new Stock.
     */
    void create(Stock stock);
}