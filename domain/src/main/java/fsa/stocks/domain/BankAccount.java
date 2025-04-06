package fsa.stocks.domain;

import java.math.BigDecimal;

/**
 * Represents a user’s bank account.
 */

public class BankAccount implements Account {
    private String iban;
    private BigDecimal balance;

    public BankAccount(String iban, BigDecimal balance) {
        this.iban = iban;
        this.balance = balance;
    }

    public BankAccount() {
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

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