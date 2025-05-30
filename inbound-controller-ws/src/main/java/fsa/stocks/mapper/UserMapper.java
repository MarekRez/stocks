package fsa.stocks.mapper;

import fsa.stocks.domain.User;
import fsa.stocks.domain.enums.UserRole;
import fsa.stocks.rest.dto.ClientModelDto;
import fsa.stocks.rest.dto.CreateUserRequestDto;
import fsa.stocks.rest.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    // Converts a CreateUserRequestDto to a domain User
    /**
     * I ignore id/bankAccount/investmentAccount here on purpose,
     * because the User constructor builds those.
     */
    @Mapping(target = "id",                ignore = true)
    @Mapping(target = "bankAccount",       ignore = true)
    @Mapping(target = "investmentAccount", ignore = true)
    default User toEntity(CreateUserRequestDto dto){
        if (dto == null) {
            return null;
        }
        // read the role out of the DTO, map the enum, and let the constructor wire up accounts
        UserRole role = UserRole.valueOf(dto.getRole().name());
        BigDecimal initial = (dto.getBankAccountBalance());

        if (initial != null) {
            // client specified a starting balance
            return new User(dto.getUsername(), dto.getEmail(), role, initial);
        } else {
            // no initial balance → use your no-arg ctor which
            // sets the default (10 000) in BankAccount()
            return new User(dto.getUsername(), dto.getEmail(), role);
        }
    }

    User toEntity(UserDto dto);

    // Converts a domain User to a ClientModelDto
    // new mapping for the frontend model:
    @Mapping(source = "bankAccount.iban",           target = "iban")
    @Mapping(source = "bankAccount.balance", target = "bankAccountBalance")
    @Mapping(source = "investmentAccount.balance", target = "investmentAccountBalance")
    ClientModelDto toClientModel(User user);

    // Converts a domain User to a UserDto for API responses
    UserDto toDto(User user);

}
