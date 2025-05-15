package fsa.stocks.mapper;

import fsa.stocks.domain.SimulationResult;
import fsa.stocks.rest.dto.SimulationRequestDto;
import fsa.stocks.rest.dto.SimulationResponseDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface SimulationMapper {

    SimulationResponseDto toDto(SimulationResult result);

}
