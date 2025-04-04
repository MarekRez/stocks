package mapper;

import fsa.stocks.domain.StockHolding;
import fsa.stocks.rest.dto.StockHoldingDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface StockHoldingMapper {

    StockHoldingDto toDto(StockHolding stockHolding);

    StockHolding toEntity(StockHoldingDto stockHoldingDto);
}
