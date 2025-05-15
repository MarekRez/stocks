package fsa.stocks.controller;

import fsa.stocks.domain.SimulationResult;
import fsa.stocks.domain.service.SimulationFacade;
import fsa.stocks.mapper.SimulationMapper;
import fsa.stocks.rest.api.PortfolioApi;
import fsa.stocks.rest.dto.SimulationRequestDto;
import fsa.stocks.rest.dto.SimulationResponseDto;
import fsa.stocks.security.oauth2_jwt.CurrentUserDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimulationController implements PortfolioApi {

    private final SimulationFacade simulationFacade;
    private final SimulationMapper        simulationMapper;
    private final CurrentUserDetailService currentUser;

    public SimulationController(SimulationFacade simulationFacade,
                                SimulationMapper simulationMapper,
                                CurrentUserDetailService currentUser) {
        this.simulationFacade = simulationFacade;
        this.simulationMapper = simulationMapper;
        this.currentUser      = currentUser;
    }

    @Override
    public ResponseEntity<SimulationResponseDto> simulateAndApplyPortfolio(SimulationRequestDto req) {
        long userId = currentUser.getUserId();
        SimulationResult result = simulationFacade.simulateAndApply(userId, req.getMonths());

        // Map domain result → DTO
        SimulationResponseDto dto = simulationMapper.toDto(result);

        // Compute the difference
        dto.setDifference(result.finalBalance().subtract(result.startBalance()));

        return ResponseEntity.ok(dto);
    }

}
