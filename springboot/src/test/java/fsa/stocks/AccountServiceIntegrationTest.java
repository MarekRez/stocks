package fsa.stocks;

import fsa.stocks.domain.Transaction;
import fsa.stocks.domain.User;
import fsa.stocks.domain.enums.TransactionType;
import fsa.stocks.domain.enums.UserRole;
import fsa.stocks.domain.repository.TransactionRepository;
import fsa.stocks.domain.repository.UserRepository;
import fsa.stocks.domain.service.AccountFacade;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@Transactional   // Rolls back after each test
class AccountServiceIntegrationTest {

    @Autowired
    private AccountFacade accountFacade;    // transactional proxy bean

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @MockitoSpyBean
    private TransactionRepository txSpy;

    private User alice;

    @BeforeEach
    void setUp() {
        // 1) Create a user with 1000 in bank, 0 in investment
        alice = new User("Alice","alice@example.com", UserRole.USER);
        alice.getBankAccount().deposit(BigDecimal.valueOf(1000));
        userRepository.create(alice);
    }

    @Test
    void depositToInvestment_shouldMoveMoneyAndRecordTransaction() {
        long id = alice.getId();

        // when
        Transaction tx = accountFacade.depositToInvestment(id, BigDecimal.valueOf(250));

        // then – balances updated
        User reloaded = userRepository.read(id);
        assertThat(reloaded.getBankAccount().getBalance()).isEqualByComparingTo("10750");
        assertThat(reloaded.getInvestmentAccount().getBalance()).isEqualByComparingTo("250");

        // and transaction persisted
        List<Transaction> allTx = transactionRepository.findByUser(reloaded);
        assertThat(allTx)
                .hasSize(1)
                .first()
                .extracting(Transaction::getType, Transaction::getAmount)
                .containsExactly(TransactionType.DEPOSIT, BigDecimal.valueOf(250));
    }

//    @Test
//    void depositToInvestment_thenThrow_rollsBackBothSides() {
//        long id = alice.getId();
//
//        // make the spy throw right after withdraw but before persisting the tx
//        doThrow(new RuntimeException("boom")).when(txSpy).create(any(Transaction.class));
//
//        // this should bubble up the exception...
//        assertThatThrownBy(() ->
//                accountFacade.depositToInvestment(id, BigDecimal.valueOf(200))
//        ).isInstanceOf(RuntimeException.class)
//                .hasMessageContaining("boom");
//
//        // ...and because it's @Transactional, everything rolls back
//        User reloaded = userRepository.read(id);
//        assertThat(reloaded.getBankAccount().getBalance())
//                .isEqualByComparingTo("11000");
//        assertThat(reloaded.getInvestmentAccount().getBalance())
//                .isEqualByComparingTo("0");
//
//        // no transaction record was saved
//        assertThat(transactionRepository.findByUser(reloaded)).isEmpty();
//    }

}
