package fsa.stocks.beans;

import fsa.stocks.transactionalFacade.TransactionalAccountFacade;
import fsa.stocks.domain.TransactionFactory;
import fsa.stocks.domain.repository.TransactionRepository;
import fsa.stocks.domain.repository.UserRepository;
import fsa.stocks.domain.service.AccountFacade;
import fsa.stocks.domain.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountBeanConfiguration {

    @Bean
    public AccountFacade accountFacade(UserRepository userRepository,
                                       TransactionFactory transactionFactory,
                                       TransactionRepository transactionRepository) {
        // 1) building the pure‐Java domain service
        AccountFacade domainSvc =
                new AccountService(transactionFactory,
                        transactionRepository,
                        userRepository);

        // 2) wrapping it in transactional proxy
        return new TransactionalAccountFacade(domainSvc);
    }

}
