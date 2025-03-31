package fsa.stocks.domain.service;

import fsa.stocks.domain.User;

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
    User get(String email);

    /**
     * Persists a new User.
     */
    void create(User user);
}