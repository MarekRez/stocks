package fsa.stocks.domain.service;

import fsa.stocks.domain.Transaction;
import fsa.stocks.domain.enums.StockSymbol;

import java.math.BigDecimal;

public interface PortfolioFacade {

    Transaction buyStock(long userId, StockSymbol  symbol, BigDecimal amount);

    Transaction sellStock(long userId, StockSymbol symbol, double shares);

}
