package fsa.stocks.domain;

import fsa.stocks.domain.enums.UserRole;

/**
 * Represents a user in the system, with bank and investment accounts.
 */

public class User {
    private long id;
    private String name; // fixme: treba spravit firtname, lastname
    private String email; // unique
    private UserRole role;
    private InvestmentAccount investmentAccount;
    private BankAccount bankAccount;

    public User(long id, String name, String email, UserRole role, InvestmentAccount investmentAccount, BankAccount bankAccount) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.investmentAccount = investmentAccount;
        this.bankAccount = bankAccount;
    }

    public  User() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public Portfolio viewPortfolio() {
        // TODO implement
        return null;
    }
}