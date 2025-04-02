package fsa.stocks;

import fsa.stocks.domain.TransactionFactory;
import fsa.stocks.domain.repository.TransactionRepository;
import fsa.stocks.domain.service.TransactionFacade;
import fsa.stocks.domain.service.TransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionBeanConfiguration {
    /**
     * Creates a TransactionFactory bean.
     * The TransactionFactory is responsible for creating Transaction objects.
     */
    @Bean
    public TransactionFactory transactionFactory() {
        return new TransactionFactory();
    }

    /**
     * Creates a TransactionFacade bean.
     * This bean wires the TransactionRepository into the TransactionService,
     * which implements the TransactionFacade interface.
     */
    @Bean
    public TransactionFacade transactionFacade(TransactionRepository transactionRepository) {
        return new TransactionService(transactionRepository);
    }
}
