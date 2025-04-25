package fsa.stocks.security.oauth2_jwt;

import fsa.stocks.domain.User;
import fsa.stocks.domain.service.UserFacade;
import fsa.stocks.rest.dto.UserDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public String getUserEmail() {
        return getCurrentUser().getEmail();
    }

    public User getFullCurrentUser() {
        return (userFacade.get(getUserEmail())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "No local user for email " + getUserEmail()
                )));
    }

    /**
     * Returns the database‐generated user ID as a primitive long.
     */
    public long getUserId() {
        return getFullCurrentUser().getId();
    }
}
