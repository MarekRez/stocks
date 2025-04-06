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
}