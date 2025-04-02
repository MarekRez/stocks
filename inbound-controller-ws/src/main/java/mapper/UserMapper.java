package mapper;

import fsa.stocks.domain.User;
import fsa.stocks.rest.dto.CreateUserRequestDto;
import fsa.stocks.rest.dto.UserDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    // Converts a CreateUserRequestDto to a domain User
    User toEntity(CreateUserRequestDto dto);

    // Converts a domain User to a UserDto for API responses
    UserDto toDto(User user);

}
