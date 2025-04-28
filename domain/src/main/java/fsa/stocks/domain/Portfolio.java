package fsa.stocks.domain;

import fsa.stocks.domain.exception.InsufficientSharesException;
import fsa.stocks.domain.exception.InvalidAmountException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Holds multiple StockHolding items owned by a user.
 */

public class Portfolio {
    private Long id;
    private List<StockHolding> holdings = new ArrayList<>();

    public Portfolio() {
    }

    public List<StockHolding> getHoldings() {
        return holdings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHoldings(List<StockHolding> holdings) {
        this.holdings = holdings;
    }

    public BigDecimal getTotalValue() {
        return holdings.stream()
                .map(holding -> {
                    BigDecimal currentPrice = holding.getStock().getCurrentPrice();
                    return currentPrice != null
                            ? currentPrice.multiply(BigDecimal.valueOf(holding.getSharesOwned()))
                            : BigDecimal.ZERO;
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Invest `amount` money into `stock` at its current price.
     * If already holding, increase that holding.
     */
    public void addInvestment(Stock stock, BigDecimal amount) {
        if (stock == null) throw new IllegalArgumentException("Stock is required");
        BigDecimal price = stock.getCurrentPrice();
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("Stock price must be set");
        }

        // how many shares?
        double shares = amount.divide(price, RoundingMode.HALF_UP).doubleValue();
        if (shares <= 0) throw new InvalidAmountException("Amount too small to buy any shares");

        // find existing holding
        Optional<StockHolding> existing = holdings.stream()
                .filter(h -> h.getStock().equals(stock))
                .findFirst();

        if (existing.isPresent()) {
            existing.get().addShares(shares);
        } else {
            holdings.add(new StockHolding(stock, shares));
        }
    }

    /**
     * Sell up to `sharesToSell`.  Returns the cash amount (= shares * price).
     */
    public BigDecimal removeInvestment(Stock stock, double sharesToSell) {
        StockHolding holding = holdings.stream()
                .filter(h -> h.getStock().equals(stock))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No holding for " + stock));

        if (sharesToSell <= 0 || sharesToSell > holding.getSharesOwned()) {
            throw new InsufficientSharesException("Tried to sell " + sharesToSell);
        }

        holding.removeShares(sharesToSell);
        if (holding.getSharesOwned() == 0) {
            holdings.remove(holding);
        }

        return stock.getCurrentPrice().multiply(BigDecimal.valueOf(sharesToSell));
    }
}