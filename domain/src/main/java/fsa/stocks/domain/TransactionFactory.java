package fsa.stocks.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

import fsa.stocks.domain.enums.TransactionType;
import fsa.stocks.domain.exception.InvalidAmountException;

public class TransactionFactory {

    public Transaction create(User user, TransactionType type, BigDecimal amount, Stock stock) {
        // Validate that the user is not null
        Objects.requireNonNull(user, "User cannot be null");
        // Validate that the transaction type is not null
        Objects.requireNonNull(type, "Transaction type cannot be null");
        // Validate that the amount is provided and greater than zero
        Objects.requireNonNull(amount, "Amount cannot be null");

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }
        // For BUY or SELL transactions, the stock must be provided
        if ((type == TransactionType.BUY || type == TransactionType.SELL) && stock == null) {
            throw new IllegalArgumentException("Stock cannot be null for BUY or SELL transactions");
        }

        // Create the transaction, setting all fields including a timestamp
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setStock(stock);
        transaction.setTimestamp(OffsetDateTime.now());

        return transaction;
    }
}
