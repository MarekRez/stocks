package fsa.stocks.jpa.adapter;

import fsa.stocks.domain.User;
import fsa.stocks.domain.repository.UserRepository;
import fsa.stocks.jpa.repository.UserSpringDataRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserRepositoryAdapter implements UserRepository {

    private final UserSpringDataRepository userSpringDataRepository;

    public JpaUserRepositoryAdapter(UserSpringDataRepository userSpringDataRepository) {
        this.userSpringDataRepository = userSpringDataRepository;
    }

    @Override
    public User read(long id) {
        Optional<User> user = userSpringDataRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public List<User> readAll() {
        return userSpringDataRepository.findAll();
    }

    @Override
    public void create(User user) {
        userSpringDataRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userSpringDataRepository.findByEmail(email);
    }

    @Override
    public void update(User user) {
        userSpringDataRepository.save(user);
    }
}
