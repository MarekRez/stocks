package fsa.stocks.domain.service;

import fsa.stocks.domain.BankAccount;
import fsa.stocks.domain.InvestmentAccount;
import fsa.stocks.domain.Portfolio;
import fsa.stocks.domain.User;
import fsa.stocks.domain.repository.UserRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Implements the UserFacade, providing the actual logic for user operations.
 */
public class UserService implements UserFacade {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(long id) {
        return userRepository.read(id);
    }

    @Override
    public Optional<User> get(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void create(User user) {
        // check if the user already exists.
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        // Create and initialize a BankAccount and InvestmentAccount
        BankAccount bankAccount = new BankAccount();
        bankAccount.setIban(bankAccount.generateIBAN());
        bankAccount.setBalance(BigDecimal.ZERO);

        user.setBankAccount(bankAccount);

        InvestmentAccount investmentAccount = new InvestmentAccount();
        investmentAccount.setBalance(BigDecimal.ZERO);

        Portfolio portfolio = new Portfolio();
        portfolio.setHoldings(new ArrayList<>());
        investmentAccount.setPortfolio(portfolio);

        user.setInvestmentAccount(investmentAccount);

        userRepository.create(user);
    }
}