package fsa.stocks.domain.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("User with id " + id + " does not exist");
    }
}
