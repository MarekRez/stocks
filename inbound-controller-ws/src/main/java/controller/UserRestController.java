package controller;

import fsa.stocks.domain.User;
import fsa.stocks.domain.service.UserFacade;
import fsa.stocks.rest.api.UsersApi;
import fsa.stocks.rest.dto.CreateUserRequestDto;
import fsa.stocks.rest.dto.UserDto;
import mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class UserRestController implements UsersApi {

    private final UserFacade userFacade;
    private final UserMapper userMapper;

    public UserRestController(UserFacade userFacade, UserMapper userMapper) {
        this.userFacade = userFacade;
        this.userMapper = userMapper;
    }

    //fixme: method should be void maybe?
    @Override
    public ResponseEntity<UserDto> createUser(CreateUserRequestDto createUserRequestDto) {
        User user = userMapper.toEntity(createUserRequestDto);
        userFacade.create(user);
        UserDto userDto = userMapper.toDto(user);
        // trying this - Set the location header to the URI of the created user
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).body(userDto);
    }

    @Override
    public ResponseEntity<UserDto> getUserById(Long id) {
        User user = userFacade.get(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserDto dto = userMapper.toDto(user);
        return ResponseEntity.ok().body(dto);
    }
}
