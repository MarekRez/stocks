package fsa.stocks.domain;

import java.math.BigDecimal;

/**
 * Represents a user’s bank account.
 */

public class BankAccount implements Account {
    private String iban;
    private BigDecimal balance;

    // Constructors, getters, setters omitted

    @Override
    public void deposit(BigDecimal amount) {
        // TODO implement
    }

    @Override
    public void withdraw(BigDecimal amount) {
        // TODO implement
    }

    private String generateIBAN() {
        // TODO implement
        return null;
    }
}