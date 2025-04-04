package mapper;

import fsa.stocks.domain.Stock;
import fsa.stocks.rest.dto.StockDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface StockMapper {

    // Convert from domain Stock to API DTO
    StockDto toDto(Stock stock);

    // Convert from API DTO to domain Stock
    Stock toEntity(StockDto stockDto);

}
