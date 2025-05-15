package fsa.stocks.domain.service;

import fsa.stocks.domain.SimulationResult;

import java.math.BigDecimal;

public interface SimulationFacade {

    /**
     * Runs an N-month simulation on the user’s portfolio,
     * updating prices and reinvesting dividends month by month,
     * then persists the new state and returns the summary.
     *
     * @param userId ID of the user
     * @param months how many months to simulate and apply
     * @return start vs. final balances
     */
    SimulationResult simulateAndApply(long userId, int months);
}
