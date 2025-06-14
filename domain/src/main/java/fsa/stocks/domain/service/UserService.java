package fsa.stocks.domain.service;
import fsa.stocks.domain.User;
import fsa.stocks.domain.exception.UserNotFoundException;
import fsa.stocks.domain.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implements the UserFacade, providing the actual logic for user operations.
 */
public class UserService implements UserFacade {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.readAll();
    }

    @Override
    public User get(long id) {
        return userRepository.read(id);
    }

    @Override
    public Optional<User> get(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void create(User user) {
        // check if the user already exists.
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        userRepository.create(user);
    }

    @Override
    public void update(User user) {
        // check if the user exists.
        User existingUser = userRepository.read(user.getId());
        Objects.requireNonNull(existingUser, "User with this id does not exist");

        userRepository.update(user);
    }

    @Override
    public void delete(long id) {
        // check if the user exists.
        User user = userRepository.read(id);
        if (user == null) {
            throw new UserNotFoundException(String.valueOf(id));
        }
        userRepository.delete(id);
    }

}