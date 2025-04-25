package fsa.stocks.domain.service;

import fsa.stocks.domain.Transaction;
import fsa.stocks.domain.TransactionFactory;
import fsa.stocks.domain.User;
import fsa.stocks.domain.enums.TransactionType;
import fsa.stocks.domain.repository.TransactionRepository;
import fsa.stocks.domain.repository.UserRepository;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

public class AccountService implements AccountFacade{

    private final TransactionFactory transactionFactory;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public AccountService(TransactionFactory transactionFactory, TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionFactory = transactionFactory;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Transaction depositToBank(Long userId, BigDecimal amount) {
        User user = loadUser(userId);
        user.getBankAccount().deposit(amount);
        Transaction tx = transactionFactory
                .create(user, TransactionType.DEPOSIT, amount, null);
        transactionRepository.create(tx);
        userRepository.update(user);
        return tx;
    }

    @Override
    public Transaction withdrawFromBank(Long userId, BigDecimal amount) {
        User user = loadUser(userId);
        user.getBankAccount().withdraw(amount);
        Transaction tx = transactionFactory
                .create(user, TransactionType.WITHDRAWAL, amount, null);
        transactionRepository.create(tx);
        userRepository.update(user);
        return tx;
    }

    @Override
    public Transaction depositToInvestment(Long userId, BigDecimal amount) {
        User user = loadUser(userId);
        // 1) withdraw from the bank
        user.getBankAccount().withdraw(amount);
        // 2) deposit to the investment
        user.getInvestmentAccount().deposit(amount);
        // 3) record the transaction
        Transaction tx = transactionFactory
                .create(user, TransactionType.DEPOSIT, amount, null);
        transactionRepository.create(tx);
        // 4) save the user
        userRepository.update(user);
        return tx;
    }

    @Override
    public Transaction withdrawFromInvestment(Long userId, BigDecimal amount) {
        User user = loadUser(userId);
        // 1) deposit into the bank
        user.getBankAccount().deposit(amount);
        // 2) withdraw from the investment
        user.getInvestmentAccount().withdraw(amount);
        // 3) record the transaction
        Transaction tx = transactionFactory
                .create(user, TransactionType.WITHDRAWAL, amount, null);
        transactionRepository.create(tx);
        // 4) save the user
        userRepository.update(user);
        return tx;
    }

    private User loadUser(Long id) {
        User u = userRepository.read(id);
        if (u == null) {
            throw new NoSuchElementException("User with id " + id + " not found");
        }
        return u;
    }

}
