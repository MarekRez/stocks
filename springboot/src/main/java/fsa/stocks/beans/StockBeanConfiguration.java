package fsa.stocks.beans;

import fsa.stocks.domain.repository.StockRepository;
import fsa.stocks.domain.service.StockFacade;
import fsa.stocks.domain.service.StockService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockBeanConfiguration {
    /**
     * Creates a StockFacade bean.
     * This bean wires the StockRepository into the StockService,
     * which implements the StockFacade interface.
     */
    @Bean
    public StockFacade stockFacade(StockRepository stockRepository) {
        return new StockService(stockRepository);
    }
}
