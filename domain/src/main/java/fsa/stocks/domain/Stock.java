package fsa.stocks.domain;

import fsa.stocks.domain.enums.StockSymbol;

import java.math.BigDecimal;

/**
 * Represents a stock with various attributes and methods to update price.
 */

public class Stock {
    private long id;
    private StockSymbol symbol;
    private String currency;
    private BigDecimal currentPrice;
    private double volatility;
    private double dividendYield;
    private double expectedReturn;

    // Constructors, getters, setters omitted

    public void updatePrice() {
        // TODO implement
    }

    private void simulatePriceChange() {
        // TODO implement
    }

    private void reinvestDividends() {
        // TODO implement
    }

    private void simulateMonth() {
        // TODO implement
    }
}