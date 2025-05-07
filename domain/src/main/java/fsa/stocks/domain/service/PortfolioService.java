package fsa.stocks.domain.service;

import fsa.stocks.domain.*;
import fsa.stocks.domain.enums.StockSymbol;
import fsa.stocks.domain.enums.TransactionType;
import fsa.stocks.domain.repository.StockRepository;
import fsa.stocks.domain.repository.TransactionRepository;
import fsa.stocks.domain.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

//    @Transactional should be later implemented in the spring layer somehow, Lord only knows how
public class PortfolioService implements PortfolioFacade{

    private final UserRepository userRepository;
    private final TransactionFactory transactionFactory;
    private final TransactionRepository transactionRepository;
    private final StockRepository stockRepository;

    public PortfolioService(UserRepository userRepository, TransactionFactory transactionFactory,
                            TransactionRepository transactionRepository, StockRepository stockRepository) {
        this.userRepository = userRepository;
        this.transactionFactory = transactionFactory;
        this.transactionRepository = transactionRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public Transaction buyStock(long userId, StockSymbol symbol, BigDecimal amount) {
        User user = loadUser(userId);
        Stock stock = stockRepository.findBySymbol(symbol)
                .orElseThrow(() -> new IllegalArgumentException("Unknown symbol"));

        // buy in the investment account
        user.getInvestmentAccount().buyStock(stock, amount);

        // record the transaction
        Transaction tx = transactionFactory.create(user, TransactionType.BUY, amount, stock);
        transactionRepository.create(tx);

        userRepository.update(user);
        return tx;
    }

    @Override
    public Transaction sellStock(long userId, StockSymbol symbol, double shares) {
        User user = loadUser(userId);
        Stock stock = stockRepository.findBySymbol(symbol)
                .orElseThrow(() -> new IllegalArgumentException("Unknown symbol"));
        // sell shares, get cash back
        BigDecimal proceeds = user.getInvestmentAccount().sellStock(stock, shares);
        // deposit proceeds into the bank account
        // user.getBankAccount().deposit(proceeds);

        // record as a SELL transaction
        Transaction tx = transactionFactory.create(user, TransactionType.SELL, proceeds, stock);
        transactionRepository.create(tx);

        userRepository.update(user);
        return tx;
    }

    @Override
    public List<StockHolding> getHoldingsForUser(Long userId) {
        User user = loadUser(userId);
        return user.getInvestmentAccount()
                .getPortfolio()
                .getHoldings();
    }

    private User loadUser(Long id) {
        User u = userRepository.read(id);
        if (u == null) {
            throw new NoSuchElementException("User with id " + id + " not found");
        }
        return u;
    }
}
