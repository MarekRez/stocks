package fsa.stocks.domain.service;

import fsa.stocks.domain.Transaction;
import fsa.stocks.domain.User;
import fsa.stocks.domain.repository.TransactionRepository;

import java.util.Collection;

/**
 * Implements the TransactionFacade, providing logic for transaction operations.
 */
public class TransactionService implements TransactionFacade {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void recordTransaction(Transaction transaction) {
        // Additional validations, e.g. checking user balance, etc.
        transactionRepository.create(transaction);
    }

    @Override
    public Collection<Transaction> findByUser(User user) {
        return transactionRepository.findByUser(user);
    }
}