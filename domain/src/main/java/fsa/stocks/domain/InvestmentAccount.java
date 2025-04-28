package fsa.stocks.domain;

import fsa.stocks.domain.exception.InsufficientFundsException;
import fsa.stocks.domain.exception.InvalidAmountException;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a user’s investment account, which holds a Portfolio.
 */

public class InvestmentAccount implements Account {
    private Long id;
    private BigDecimal balance;
    private Portfolio portfolio;

    public InvestmentAccount() {
        this(BigDecimal.ZERO, new Portfolio());
    }

    public InvestmentAccount(BigDecimal initialBalance, Portfolio portfolio) {
        this.balance   = initialBalance != null ? initialBalance : BigDecimal.ZERO;
        this.portfolio = portfolio != null ? portfolio : new Portfolio();
    }

    public Long getId() {
        return id;
    }

    @Override
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

    //    ONLY ADDING MONEY, REST IS IN SERVICE
    @Override
    public void deposit(BigDecimal amount) {
        validateAmount(amount);
        balance = balance.add(amount);
    }

    //    ONLY REMOVING MONEY, REST IS IN SERVICE
    @Override
    public void withdraw(BigDecimal amount) {
        validateAmount(amount);
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException(
                    "Cannot withdraw " + amount + " from investment account – balance is only " + balance);
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

    /**
     * Buy stock using money already in the investment account.
     * Throws if insufficient funds or invalid amount.
     */
    public void buyStock(Stock stock, BigDecimal amount) {
        // ensure there is enough money
        withdraw(amount);
        portfolio.addInvestment(stock, amount);
    }

    /**
     * Sell some shares back into cash.
     * Returns the cash proceeds, which the caller can deposit back to the bank.
     */
    public BigDecimal sellStock(Stock stock, double shares) {
        BigDecimal proceeds = portfolio.removeInvestment(stock, shares);
        // put the cash back in the investment balance
        deposit(proceeds);
        return proceeds;
    }
}