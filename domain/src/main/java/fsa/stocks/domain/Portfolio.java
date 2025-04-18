package fsa.stocks.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
        // TODO implement
        return BigDecimal.ZERO;
    }
}