package fsa.stocks.domain.service;

import fsa.stocks.domain.User;

import java.util.Optional;

/**
 * Exposes operations for managing User entities.
 */
public interface UserFacade {

    /**
     * Retrieves a User by its ID.
     */
    User get(long id);

    /**
     * Retrieves a User by email address.
     */
    Optional<User> get(String email);

    /**
     * Persists a new User.
     */
    void create(User user);
}