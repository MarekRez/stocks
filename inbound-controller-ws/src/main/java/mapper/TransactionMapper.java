package mapper;

import fsa.stocks.domain.Transaction;
import fsa.stocks.rest.dto.TransactionDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {UserMapper.class, StockMapper.class})
public interface TransactionMapper {

    // Converts a domain Transaction to its DTO representation
    TransactionDto toDto(Transaction transaction);

    // Converts a Transaction DTO to the domain Transaction
    Transaction toEntity(TransactionDto transactionDto);

}
