package fsa.stocks.jpa.repository;

import fsa.stocks.domain.Stock;
import fsa.stocks.domain.enums.StockSymbol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockSpringDataRepository extends JpaRepository<Stock,Long> {
    Optional<Stock> findBySymbol(StockSymbol symbol);

}
