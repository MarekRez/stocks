package fsa.stocks.domain;

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


    void addShares(double shares) {
        if (shares < 0 || shares + this.sharesOwned < 0) {
            throw new IllegalArgumentException("Cannot add negative shares");
        }
        this.sharesOwned += shares;
    }

    void removeShares(double shares) {
        if (shares > this.sharesOwned) {
            throw new IllegalArgumentException("Cannot remove more shares than owned");
        }
        this.sharesOwned -= shares;
    }
}