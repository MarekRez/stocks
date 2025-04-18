package fsa.stocks.domain.repository;

import fsa.stocks.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User read(long id);
    List<User> readAll();
    void create(User user);
    Optional<User> findByEmail(String email);
    void update(User user);
    void delete(long id);
}