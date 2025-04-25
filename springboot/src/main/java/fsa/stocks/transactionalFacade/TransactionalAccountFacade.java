package fsa.stocks.transactionalFacade;

import fsa.stocks.domain.Transaction;
import fsa.stocks.domain.service.AccountFacade;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public class TransactionalAccountFacade implements AccountFacade {

    private final AccountFacade delegate;

    public TransactionalAccountFacade(AccountFacade delegate) {
        this.delegate = delegate;
    }

    @Override
    @Transactional
    public Transaction depositToBank(Long userId, BigDecimal amount) {
        return delegate.depositToBank(userId, amount);
    }
    @Override
    @Transactional
    public Transaction withdrawFromBank(Long userId, BigDecimal amount) {
        return delegate.withdrawFromBank(userId, amount);
    }

    @Override
    @Transactional
    public Transaction depositToInvestment(Long userId, BigDecimal amount) {
        return delegate.depositToInvestment(userId, amount);
    }

    @Override
    @Transactional
    public Transaction withdrawFromInvestment(Long userId, BigDecimal amount) {
        return delegate.withdrawFromInvestment(userId, amount);
    }
}
