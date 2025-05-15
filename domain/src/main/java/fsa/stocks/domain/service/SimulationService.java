package fsa.stocks.domain.service;

import fsa.stocks.domain.SimulationResult;
import fsa.stocks.domain.Stock;
import fsa.stocks.domain.StockHolding;
import fsa.stocks.domain.User;
import fsa.stocks.domain.repository.UserRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class SimulationService implements SimulationFacade{

    private final UserRepository userRepository;

    public SimulationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override // fixme: should be transactional somehow
    public SimulationResult simulateAndApply(long userId, int months) {

        User user = userRepository.read(userId);
        Objects.requireNonNull(user, "User with id " + userId + " not found");

        // capture starting value
        BigDecimal start = user.getInvestmentAccount()
                .getPortfolio()
                .getTotalValue();

        // simulate each month
        for (int m = 1; m <= months; m++) {
            for (StockHolding h : user.getInvestmentAccount().getPortfolio().getHoldings()) {
                Stock s = h.getStock();

                // 1) GBM price update - Geometric Brownian Motion equation
                BigDecimal oldPrice = s.getCurrentPrice();
                double mu    = s.getExpectedReturn();
                double sigma = s.getVolatility();
                double dt    = 1.0 / 12.0;
                double Z     = ThreadLocalRandom.current().nextGaussian();

                // μ − ½σ² is the “drift” term
                double drift     = (mu - 0.5 * sigma * sigma) * dt;
                double diffusion = sigma * Math.sqrt(dt) * Z;

                // applying the GBM step, then rounding the price to 2 decimals
                BigDecimal rawPrice = oldPrice.multiply(
                        BigDecimal.valueOf(Math.exp(drift + diffusion))
                );
                BigDecimal newPrice = rawPrice.setScale(2, RoundingMode.HALF_UP);
                s.setCurrentPrice(newPrice);

                // 2) Dividend reinvestment
                BigDecimal shares     = BigDecimal.valueOf(h.getSharesOwned());
                BigDecimal monthlyDiv = shares
                        .multiply(newPrice)
                        .multiply(BigDecimal.valueOf(s.getDividendYield()))
                        .divide(BigDecimal.valueOf(12), 8, RoundingMode.HALF_UP);

                // compute extra shares
                BigDecimal extraShares = monthlyDiv
                        .divide(newPrice, 4, RoundingMode.HALF_UP);
                h.addShares(extraShares.doubleValue());

            }
}
        userRepository.update(user);

        BigDecimal end = user.getInvestmentAccount()
                .getPortfolio()
                .getTotalValue();

        return new SimulationResult(start, end);

    }

}
