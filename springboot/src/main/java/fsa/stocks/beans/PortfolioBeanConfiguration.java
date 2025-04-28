package fsa.stocks.beans;

import fsa.stocks.domain.TransactionFactory;
import fsa.stocks.domain.repository.StockRepository;
import fsa.stocks.domain.repository.TransactionRepository;
import fsa.stocks.domain.repository.UserRepository;
import fsa.stocks.domain.service.PortfolioFacade;
import fsa.stocks.domain.service.PortfolioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortfolioBeanConfiguration {
    /**
     * Creates a PortfolioFacade bean.
     * This bean wires the PortfolioRepository into the PortfolioService,
     * which implements the PortfolioFacade interface.
     */
    @Bean
    public PortfolioFacade portfolioFacade(UserRepository userRepository,
                                           TransactionFactory transactionFactory,
                                           TransactionRepository transactionRepository,
                                           StockRepository stockRepository)
    {
        return new PortfolioService(userRepository, transactionFactory, transactionRepository, stockRepository);
    }
}