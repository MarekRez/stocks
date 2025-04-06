package fsa.stocks.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * Holds multiple StockHolding items owned by a user.
 */

public class Portfolio {
    private Long id;
    private List<StockHolding> holdings;

    public Portfolio() {
    }

    public Portfolio(Long id, List<StockHolding> holdings) {
        this.id = id;
        this.holdings = holdings;
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
        // TODO implement
        return BigDecimal.ZERO;
    }
}