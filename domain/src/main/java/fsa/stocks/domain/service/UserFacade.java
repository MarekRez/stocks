package fsa.stocks.domain.service;

import fsa.stocks.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * Exposes operations for managing User entities.
 */
public interface UserFacade {

    /**
     * Retrieves a User by its ID.
     */
    // maybe change name to getUserById
    User get(long id);

    /**
     * Retrieves a User by email address.
     */
    Optional<User> get(String email);

    /**
     * Retrieves all Users.
     */
    List<User> getAll();

    /**
     * Persists a new User.
     */
    void create(User user);

    /**
     * Updates an existing User.
     */
    void update(User user);

    /**
     * Deletes a User by its ID.
     */
    void delete(long id);
}