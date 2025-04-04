package fsa.stocks.domain;

import fsa.stocks.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;

/**
 * Represents a financial transaction (buy, sell, deposit, withdrawal).
 */

public class Transaction {
    private long id;
    private User user;
    private TransactionType type;
    private BigDecimal amount;
    private Stock stock; // optional
    private OffsetDateTime timestamp;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }
}