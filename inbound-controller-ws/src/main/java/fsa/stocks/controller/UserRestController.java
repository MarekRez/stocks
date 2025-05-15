package fsa.stocks.controller;

import fsa.stocks.domain.User;
import fsa.stocks.domain.service.UserFacade;
import fsa.stocks.rest.api.UsersApi;
import fsa.stocks.rest.dto.ClientModelDto;
import fsa.stocks.rest.dto.CreateUserRequestDto;
import fsa.stocks.rest.dto.UserDto;
import fsa.stocks.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserRestController implements UsersApi {

    private final UserFacade userFacade;
    private final UserMapper userMapper;

    public UserRestController(UserFacade userFacade, UserMapper userMapper) {
        this.userFacade = userFacade;
        this.userMapper = userMapper;
    }

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
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UserDto> getUserById(Long id) {
        User user = userFacade.get(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserDto dto = userMapper.toDto(user);
        return ResponseEntity.ok().body(dto);
    }

    @Override
    public ResponseEntity<UserDto> getUserByEmail(String email) {
        User user = userFacade.get(email).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserDto dto = userMapper.toDto(user);
        return ResponseEntity.ok().body(dto);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteUser(Long id) {
        userFacade.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ClientModelDto>> getAllUsers() {
        List<User> users = userFacade.getAll();
        List<ClientModelDto> clientModels  = users.stream()
                .map(userMapper::toClientModel)
                .toList();
        return ResponseEntity.ok().body(clientModels);
    }

    // fix: maybe UpdateUserRequestDto instead of CreateUserRequestDto
    @Override
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UserDto> updateUser(Long id, CreateUserRequestDto createUserRequestDto) {
        User user = userMapper.toEntity(createUserRequestDto);
        user.setId(id);
        userFacade.update(user);
        UserDto userDto = userMapper.toDto(user);
        return ResponseEntity.ok().body(userDto);
    }

}
