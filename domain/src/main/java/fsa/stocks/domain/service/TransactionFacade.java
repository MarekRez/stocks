package fsa.stocks.domain.service;

import fsa.stocks.domain.Transaction;
import fsa.stocks.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * Exposes operations for managing Transaction entities.
 */
public interface TransactionFacade {

//    /**
//     * Records a new transaction.
//     */
//    void recordTransaction(Transaction transaction);

    /**
     * Finds all transactions for a given user.
     */
    List<Transaction> findByUser(User user);
}