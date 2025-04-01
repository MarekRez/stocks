package fsa.stocks.domain.service;

import fsa.stocks.domain.User;
import fsa.stocks.domain.repository.UserRepository;

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
    public User get(long id) {
        return userRepository.read(id);
    }

    @Override
    public Optional<User> get(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void create(User user) {
        // You could add validations here before persisting the user.
        userRepository.create(user);
    }
}