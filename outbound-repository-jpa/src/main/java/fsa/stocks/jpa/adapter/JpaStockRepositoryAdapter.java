package fsa.stocks.jpa.adapter;

import fsa.stocks.domain.Stock;
import fsa.stocks.domain.enums.StockSymbol;
import fsa.stocks.domain.repository.StockRepository;
import fsa.stocks.jpa.repository.StockSpringDataRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaStockRepositoryAdapter implements StockRepository {
    private final StockSpringDataRepository stockSpringDataRepository;

    public JpaStockRepositoryAdapter(StockSpringDataRepository stockSpringDataRepository) {
        this.stockSpringDataRepository = stockSpringDataRepository;
    }

    @Override
    public Optional<Stock> findBySymbol(StockSymbol symbol) {
        return stockSpringDataRepository.findBySymbol(symbol);
    }

    @Override
    public List<Stock> readAll() {
        return stockSpringDataRepository.findAll();
    }

    @Override
    public void create(Stock stock) {
        stockSpringDataRepository.save(stock);
    }
}
