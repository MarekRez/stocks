package fsa.stocks;

import fsa.stocks.domain.repository.UserRepository;
import fsa.stocks.domain.service.UserFacade;
import fsa.stocks.domain.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeanConfiguration {
    /**
     * Creates a UserFacade bean.
     * This bean wires the domain’s UserRepository into the UserService,
     * which implements the UserFacade interface.
     */
    @Bean
    public UserFacade userFacade(UserRepository userRepository) {
        return new UserService(userRepository);
    }
}
