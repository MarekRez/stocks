package fsa.stocks.domain.service;

import fsa.stocks.domain.Stock;
import fsa.stocks.domain.enums.StockSymbol;

import java.util.Collection;

/**
 * Exposes operations for managing Stock entities.
 */
public interface StockFacade {

    /**
     * Retrieves a Stock by its symbol.
     */
    Stock get(StockSymbol symbol);

    /**
     * Returns all available stocks in the system.
     */
    Collection<Stock> listAll();

    /**
     * Persists a new Stock.
     */
    void create(Stock stock);
}