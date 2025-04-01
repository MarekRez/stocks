package fsa.stocks.domain.repository;

import fsa.stocks.domain.Transaction;
import fsa.stocks.domain.User;

import java.util.List;

public interface TransactionRepository {
    List<Transaction> findByUser(User user);
    void create(Transaction transaction);
}