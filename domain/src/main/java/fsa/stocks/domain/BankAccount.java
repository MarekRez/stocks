package fsa.stocks.domain;

import fsa.stocks.domain.exception.InsufficientFundsException;
import fsa.stocks.domain.exception.InvalidAmountException;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Represents a user’s bank account.
 */

public class BankAccount implements Account {
    private String iban;
    private BigDecimal balance;

    public BankAccount() {
        this.iban = generateIBAN();
//        this.balance = BigDecimal.ZERO;
        this.balance = new BigDecimal(10000);
    }

    public BankAccount(BigDecimal balance) {
        this.iban = generateIBAN();
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public void deposit(BigDecimal amount) {
        validateAmount(amount);
        balance = balance.add(amount);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        validateAmount(amount);
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException(
                    "Cannot withdraw " + amount + " from bank account " + iban +
                            " – balance is only " + balance);
        }
        balance = balance.subtract(amount);
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null) {
            throw new InvalidAmountException("Amount cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException(
                    "Amount must be greater than zero, was: " + amount);
        }
    }

    public String generateIBAN() {
        String characters = "0123456789000000";
        StringBuilder iban = new StringBuilder("SK-");
        Random rand = new Random();
        for (int i = 0; i < 16; i++) {
            iban.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return iban.toString();
    }
}