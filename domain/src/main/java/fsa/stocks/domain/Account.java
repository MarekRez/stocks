package fsa.stocks.domain;

import fsa.stocks.domain.exception.InsufficientFundsException;
import fsa.stocks.domain.exception.InvalidAmountException;

import java.math.BigDecimal;

/**
 * Represents a general account with deposit/withdraw operations.
 */

public interface Account {
    /**
     * @param amount must be > 0
     * @throws InvalidAmountException if amount is null or ≤ 0
     */
    void deposit(BigDecimal amount) throws InvalidAmountException;

    /**
     * @param amount must be > 0 and ≤ current balance
     * @throws InvalidAmountException   if amount is null or ≤ 0
     * @throws InsufficientFundsException if amount > balance
     */
    void withdraw(BigDecimal amount)
            throws InvalidAmountException, InsufficientFundsException;

    BigDecimal getBalance();
}
