package fsa.stocks.controller;

import fsa.stocks.domain.Transaction;
import fsa.stocks.domain.service.AccountFacade;
import fsa.stocks.mapper.TransactionMapper;
import fsa.stocks.rest.api.BankApi;
import fsa.stocks.rest.api.InvestmentApi;
import fsa.stocks.rest.dto.AmountDto;
import fsa.stocks.rest.dto.TransactionDto;
import fsa.stocks.security.oauth2_jwt.CurrentUserDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountController implements BankApi, InvestmentApi {

    private final AccountFacade accountFacade;
    private final CurrentUserDetailService currentUser;
    private final TransactionMapper transactionMapper;

    public AccountController(AccountFacade accountFacade, CurrentUserDetailService currentUser, TransactionMapper transactionMapper) {
        this.accountFacade = accountFacade;
        this.currentUser = currentUser;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public ResponseEntity<TransactionDto> depositToBank(AmountDto dto) {
        long userId = currentUser.getUserId();
        Transaction tx = accountFacade.depositToBank(userId, BigDecimal.valueOf(dto.getAmount()));
        return ResponseEntity.ok(transactionMapper.toDto(tx));
    }

    @Override
    public ResponseEntity<TransactionDto> withdrawFromBank(AmountDto dto) {
        long userId = currentUser.getUserId();
        Transaction tx = accountFacade.withdrawFromBank(userId, BigDecimal.valueOf(dto.getAmount()));
        return ResponseEntity.ok(transactionMapper.toDto(tx));
    }

    @Override
    public ResponseEntity<TransactionDto> depositToInvestment(AmountDto dto) {
        long userId = currentUser.getUserId();
        Transaction tx = accountFacade.depositToInvestment(userId, BigDecimal.valueOf(dto.getAmount()));
        return ResponseEntity.ok(transactionMapper.toDto(tx));
    }

    @Override
    public ResponseEntity<TransactionDto> withdrawFromInvestment(AmountDto dto) {
        long userId = currentUser.getUserId();
        Transaction tx = accountFacade.withdrawFromInvestment(userId, BigDecimal.valueOf(dto.getAmount()));
        return ResponseEntity.ok(transactionMapper.toDto(tx));
    }
}
