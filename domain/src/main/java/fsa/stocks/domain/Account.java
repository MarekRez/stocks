package fsa.stocks.domain;

import java.math.BigDecimal;

/**
 * Represents a general account with deposit/withdraw operations.
 */

public interface Account {
    void deposit(BigDecimal amount);
    void withdraw(BigDecimal amount);
}
