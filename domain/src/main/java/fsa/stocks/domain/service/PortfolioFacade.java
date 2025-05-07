package fsa.stocks.domain.service;

import fsa.stocks.domain.StockHolding;
import fsa.stocks.domain.Transaction;
import fsa.stocks.domain.enums.StockSymbol;

import java.math.BigDecimal;
import java.util.List;

public interface PortfolioFacade {

    /**
     * Buys a stock for the user.
     *
     * @param userId the ID of the user
     * @param symbol the stock symbol to buy
     * @param amount the amount to invest in the stock
     * @return the transaction representing the purchase
     */
    Transaction buyStock(long userId, StockSymbol  symbol, BigDecimal amount);

    /**
     * Sells a stock for the user.
     *
     * @param userId the ID of the user
     * @param symbol the stock symbol to sell
     * @param shares the number of shares to sell
     * @return the transaction representing the sale
     */
    Transaction sellStock(long userId, StockSymbol symbol, double shares);

    /**
     * Gets the stock holdings for a user.
     *
     * @param userId the ID of the user
     * @return a list of stock holdings for the user
     */
    List<StockHolding> getHoldingsForUser(Long userId);

}
