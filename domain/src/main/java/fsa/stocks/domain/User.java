package fsa.stocks.domain;

import fsa.stocks.domain.enums.UserRole;

import java.math.BigDecimal;

/**
 * Represents a user in the system, with bank and investment accounts.
 */

public class User {
    private long id;
    private String username;
    private String email; // unique
    private UserRole role;
    private BankAccount bankAccount;
    private InvestmentAccount investmentAccount;

    public User(String username, String email, UserRole role) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.bankAccount = new BankAccount();
        this.investmentAccount = new InvestmentAccount();

    }

    public User(String username, String email, UserRole role, BigDecimal balance) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.bankAccount = new BankAccount(balance);
        this.investmentAccount = new InvestmentAccount();
    }

    public  User() {}

    // Getters
    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public InvestmentAccount getInvestmentAccount() {
        return investmentAccount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setInvestmentAccount(InvestmentAccount investmentAccount) {
        this.investmentAccount = investmentAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

}