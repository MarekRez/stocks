package fsa.stocks.domain;

import fsa.stocks.domain.enums.UserRole;

/**
 * Represents a user in the system, with bank and investment accounts.
 */

public class User {
    private long id;
    private String name;
    private String email; // unique
    private UserRole role;
    private InvestmentAccount investmentAccount;
    private BankAccount bankAccount;

    // Constructors, getters, setters omitted

    public Portfolio viewPortfolio() {
        // TODO implement
        return null;
    }
}