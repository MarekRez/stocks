package fsa.stocks.domain.service;

import fsa.stocks.domain.Transaction;

import java.math.BigDecimal;

public interface AccountFacade {

    /**
     * Deposits the specified amount into the user's bank account.
     *
     * @param userId the ID of the user
     * @param amount the amount to deposit
     * @return the transaction details
     */
    Transaction depositToBank(Long userId, BigDecimal amount);

    Transaction withdrawFromBank(Long userId, BigDecimal amount);

    Transaction depositToInvestment(Long userId, BigDecimal amount);

    Transaction withdrawFromInvestment(Long userId, BigDecimal amount);
}
