package fsa.stocks.domain.repository;

import fsa.stocks.domain.Transaction;
import fsa.stocks.domain.User;

import java.util.Collection;

public interface TransactionRepository {
    Collection<Transaction> findByUser(User user);
    void create(Transaction transaction);
}