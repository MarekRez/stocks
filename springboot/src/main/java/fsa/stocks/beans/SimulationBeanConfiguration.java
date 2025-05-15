package fsa.stocks.beans;

import fsa.stocks.domain.repository.UserRepository;
import fsa.stocks.domain.service.SimulationFacade;
import fsa.stocks.domain.service.SimulationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimulationBeanConfiguration {

    @Bean
    public SimulationFacade simulationFacade(UserRepository userRepository) {
        return new SimulationService(userRepository);
    }
}
