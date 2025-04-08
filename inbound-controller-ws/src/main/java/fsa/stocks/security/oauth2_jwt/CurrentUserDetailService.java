package fsa.stocks.security.oauth2_jwt;

import fsa.stocks.domain.User;
import fsa.stocks.domain.service.UserFacade;
import fsa.stocks.rest.dto.UserDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrentUserDetailService {

    private final UserFacade userFacade;

    public CurrentUserDetailService(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public UserDto getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDto) {
            return (UserDto) principal;
        }

        throw new IllegalStateException("Current user is not of type UserDto.");
    }

    public Long getUserId() {
        return getCurrentUser().getId();
    }

    public String getUserEmail() {
        return getCurrentUser().getEmail();
    }

    public Optional<User> getFullCurrentUser() {
        return userFacade.get(getUserEmail());
    }

}
