package fsa.stocks.domain.service;

import fsa.stocks.domain.Stock;
import fsa.stocks.domain.enums.StockSymbol;
import fsa.stocks.domain.repository.StockRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implements the StockFacade, providing the actual logic for stock operations.
 */
public class StockService implements StockFacade {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Optional<Stock> get(StockSymbol symbol) {
        return stockRepository.findBySymbol(symbol);
    }

    @Override
    public List<Stock> listAll() {
        return stockRepository.readAll();
    }

    @Override
    public void create(Stock stock) {
        // check if the stock already exists.
        Optional<Stock> existingStock = stockRepository.findBySymbol(stock.getSymbol());
        if (existingStock.isPresent()) {
            throw new IllegalArgumentException("Stock with this symbol already exists");
        }
        stockRepository.create(stock);
    }
}