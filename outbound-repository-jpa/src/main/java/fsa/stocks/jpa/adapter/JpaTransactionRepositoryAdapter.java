package fsa.stocks.jpa.adapter;

import fsa.stocks.domain.Transaction;
import fsa.stocks.domain.User;
import fsa.stocks.domain.repository.TransactionRepository;
import fsa.stocks.jpa.repository.TransactionSpringDataRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaTransactionRepositoryAdapter implements TransactionRepository {
    private final TransactionSpringDataRepository transactionSpringDataRepository;

    public JpaTransactionRepositoryAdapter(TransactionSpringDataRepository transactionSpringDataRepository) {
        this.transactionSpringDataRepository = transactionSpringDataRepository;
    }

    @Override
    public List<Transaction> findByUser(User user) {
        return transactionSpringDataRepository.findByUser(user);
    }

    @Override
    public void create(Transaction transaction) {
        transactionSpringDataRepository.save(transaction);
    }
}
