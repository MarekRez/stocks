package fsa.stocks.jpa.repository;

import fsa.stocks.domain.Transaction;
import fsa.stocks.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionSpringDataRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
}
