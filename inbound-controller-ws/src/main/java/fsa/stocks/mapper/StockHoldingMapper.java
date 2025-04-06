package fsa.stocks.mapper;

import fsa.stocks.domain.StockHolding;
import fsa.stocks.rest.dto.StockHoldingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")

public interface StockHoldingMapper {
    @Mapping(target = "id", ignore = true)
    StockHoldingDto toDto(StockHolding stockHolding);

    StockHolding toEntity(StockHoldingDto stockHoldingDto);
}
