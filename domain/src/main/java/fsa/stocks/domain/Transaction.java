package fsa.stocks.domain;

import fsa.stocks.domain.enums.TransactionType;

import java.math.BigDecimal;
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
    private Date timestamp;

    // Constructors, getters, setters omitted
}