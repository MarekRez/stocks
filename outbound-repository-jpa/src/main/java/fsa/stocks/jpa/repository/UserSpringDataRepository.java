package fsa.stocks.jpa.repository;

import fsa.stocks.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSpringDataRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
