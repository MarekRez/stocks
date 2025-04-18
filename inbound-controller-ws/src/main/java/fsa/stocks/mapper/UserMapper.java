package fsa.stocks.mapper;

import fsa.stocks.domain.User;
import fsa.stocks.domain.enums.UserRole;
import fsa.stocks.rest.dto.CreateUserRequestDto;
import fsa.stocks.rest.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    // Converts a CreateUserRequestDto to a domain User
    /**
     * We ignore id/bankAccount/investmentAccount here on purpose,
     * because we want the User constructor to build those for us.
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
        return new User(dto.getName(), dto.getEmail(), role);
    }
    User toEntity(UserDto dto);

    // Converts a domain User to a UserDto for API responses
    UserDto toDto(User user);

}
