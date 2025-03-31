package fsa.stocks.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * Holds multiple StockHolding items owned by a user.
 */

public class Portfolio {
    private List<StockHolding> holdings;

    // Constructors, getters, setters omitted

    public BigDecimal getTotalValue() {
        // TODO implement
        return BigDecimal.ZERO;
    }
}