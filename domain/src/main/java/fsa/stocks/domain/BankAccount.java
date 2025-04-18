package fsa.stocks.domain;

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

    public String getIban() {
        return iban;
    }

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
        // TODO implement
    }

    @Override
    public void withdraw(BigDecimal amount) {
        // TODO implement
    }

    public String generateIBAN() {
        String characters = "0123456789000000";
        StringBuilder iban = new StringBuilder("SK-");
        Random rand = new Random();
        for (int i = 0; i < 16; i++) {
            int index = rand.nextInt(characters.length());
            iban.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return iban.toString();
    }
}