package fsa.stocks.domain;

import java.math.BigDecimal;

/**
 * Represents a user’s investment account, which holds a Portfolio.
 */

public class InvestmentAccount implements Account {
    private Long id;
    private BigDecimal balance;
    private Portfolio portfolio;

    public InvestmentAccount(Long id, BigDecimal balance, Portfolio portfolio) {
        this.id = id;
        this.balance = balance;
        this.portfolio = portfolio;
    }

    public InvestmentAccount() {
        // Default constructor
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }


    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public void deposit(BigDecimal amount) {
        // TODO implement
    }

    @Override
    public void withdraw(BigDecimal amount) {
        // TODO implement
    }

    public void buyStock(Stock stock, BigDecimal amount) {
        // TODO implement
    }

    public void sellStock(Stock stock, BigDecimal amount) {
        // TODO implement
    }
}