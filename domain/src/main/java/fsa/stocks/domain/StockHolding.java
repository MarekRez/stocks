package fsa.stocks.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A specific stock holding in a user’s portfolio.
 */

public class StockHolding {
    private Stock stock;
    private double sharesOwned;


    public StockHolding(Stock stock, double sharesOwned) {
        this.stock = stock;
        this.sharesOwned = sharesOwned;
    }

    public StockHolding() {
        // Default constructor
    }

    public Stock getStock() {
        return stock;
    }

    public double getSharesOwned() {
        return sharesOwned;
    }

    public void setSharesOwned(double sharesOwned) {
        this.sharesOwned = sharesOwned;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }


    public void addShares(double shares) {
        if (shares < 0 || shares + this.sharesOwned < 0) {
            throw new IllegalArgumentException("Cannot add negative shares");
        }
        this.sharesOwned = round(this.sharesOwned + shares);
    }

    void removeShares(double shares) {
        if (shares < 0) {
            throw new IllegalArgumentException("Cannot remove negative shares");
        }
        double owned   = round(this.sharesOwned);
        double removal = round(shares);

        if (removal > owned) {
            throw new IllegalArgumentException(
                    "Cannot remove " + removal + " shares; only " + owned + " available"
            );
        }
        this.sharesOwned = round(owned - removal);
    }

    /** Always rounds a raw double to 2 decimal places (HALF_UP). */
    private static double round(double val) {
        return BigDecimal.valueOf(val)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}