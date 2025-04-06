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

    public Stock(long id, StockSymbol symbol, String currency, BigDecimal currentPrice, double volatility,
                 double dividendYield, double expectedReturn) {
        this.id = id;
        this.symbol = symbol;
        this.currency = currency;
        this.currentPrice = currentPrice;
        this.volatility = volatility;
        this.dividendYield = dividendYield;
        this.expectedReturn = expectedReturn;
    }

    public Stock() {
        // Default constructor
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StockSymbol getSymbol() {
        return symbol;
    }

    public void setSymbol(StockSymbol symbol) {
        this.symbol = symbol;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getVolatility() {
        return volatility;
    }

    public void setVolatility(double volatility) {
        this.volatility = volatility;
    }

    public double getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(double dividendYield) {
        this.dividendYield = dividendYield;
    }

    public double getExpectedReturn() {
        return expectedReturn;
    }

    public void setExpectedReturn(double expectedReturn) {
        this.expectedReturn = expectedReturn;
    }

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