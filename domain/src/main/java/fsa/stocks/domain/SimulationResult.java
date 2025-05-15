package fsa.stocks.domain;

import java.math.BigDecimal;

/**
 * Holds the result of a simulation.
 * It contains the start and final balance of the user’s portfolio.
 */
public record SimulationResult(BigDecimal startBalance, BigDecimal finalBalance) {

}