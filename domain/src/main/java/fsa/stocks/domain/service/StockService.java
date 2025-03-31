package fsa.stocks.domain.service;

import fsa.stocks.domain.Stock;
import fsa.stocks.domain.enums.StockSymbol;
import fsa.stocks.domain.repository.StockRepository;

import java.util.Collection;

/**
 * Implements the StockFacade, providing the actual logic for stock operations.
 */
public class StockService implements StockFacade {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock get(StockSymbol symbol) {
        return stockRepository.findBySymbol(symbol);
    }

    @Override
    public Collection<Stock> listAll() {
        return stockRepository.readAll();
    }

    @Override
    public void create(Stock stock) {
        // Additional checks or logic before creating
        stockRepository.create(stock);
    }
}