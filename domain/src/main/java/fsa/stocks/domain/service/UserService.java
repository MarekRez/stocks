package fsa.stocks.domain.service;

import fsa.stocks.domain.BankAccount;
import fsa.stocks.domain.InvestmentAccount;
import fsa.stocks.domain.Portfolio;
import fsa.stocks.domain.User;
import fsa.stocks.domain.repository.UserRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
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
        User existingUser = userRepository.read(id);
        Objects.requireNonNull(existingUser, "User with this id does not exist");

        userRepository.delete(id);
    }

}