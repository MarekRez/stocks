package fsa.stocks.domain;

import java.math.BigDecimal;

/**
 * Represents a user’s investment account, which holds a Portfolio.
 */

public class InvestmentAccount implements Account {
    private Long id;
    private BigDecimal balance;
    private Portfolio portfolio;

    // Constructors, getters, setters omitted

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