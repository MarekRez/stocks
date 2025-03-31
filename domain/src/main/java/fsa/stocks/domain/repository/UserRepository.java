package fsa.stocks.domain.repository;

import fsa.stocks.domain.User;

import java.util.Collection;

public interface UserRepository {
    User read(long id);
    Collection<User> readAll();
    void create(User user);
    User findByEmail(String email);
    void update(User user);
}