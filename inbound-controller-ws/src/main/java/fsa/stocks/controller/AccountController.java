package fsa.stocks.controller;

import fsa.stocks.domain.StockHolding;
import fsa.stocks.domain.Transaction;
import fsa.stocks.domain.enums.StockSymbol;
import fsa.stocks.domain.service.AccountFacade;
import fsa.stocks.domain.service.PortfolioFacade;
import fsa.stocks.mapper.StockHoldingMapper;
import fsa.stocks.mapper.TransactionMapper;
import fsa.stocks.rest.api.BankApi;
import fsa.stocks.rest.api.InvestmentApi;
import fsa.stocks.rest.dto.*;
import fsa.stocks.security.oauth2_jwt.CurrentUserDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AccountController implements BankApi, InvestmentApi {

    private final AccountFacade accountFacade;
    private final PortfolioFacade portfolioFacade;
    private final CurrentUserDetailService currentUser;
    private final TransactionMapper transactionMapper;
    private final StockHoldingMapper stockHoldingMapper;

    public AccountController(AccountFacade accountFacade, PortfolioFacade portfolioFacade,
                             CurrentUserDetailService currentUser, TransactionMapper transactionMapper, StockHoldingMapper stockHoldingMapper) {
        this.accountFacade = accountFacade;
        this.portfolioFacade = portfolioFacade;
        this.currentUser = currentUser;
        this.transactionMapper = transactionMapper;
        this.stockHoldingMapper = stockHoldingMapper;

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

    @Override
    public ResponseEntity<TransactionDto> buyStock(BuyRequestDto dto) {
        long uid = currentUser.getUserId();
        Transaction tx = portfolioFacade.buyStock(uid, StockSymbol.valueOf(dto.getSymbol().name()), BigDecimal.valueOf(dto.getAmount()));
        return ResponseEntity.ok(transactionMapper.toDto(tx));
    }

    @Override
    public ResponseEntity<TransactionDto> sellStock(SellRequestDto dto) {
        long uid = currentUser.getUserId();
        Transaction tx = portfolioFacade.sellStock(uid, StockSymbol.valueOf(dto.getSymbol().name()),
                dto.getShares());
        return ResponseEntity.ok(transactionMapper.toDto(tx));
    }

    @Override
    public ResponseEntity<List<StockHoldingDto>> getPortfolioHoldings() {
        long uid = currentUser.getUserId();
        List<StockHolding> holdings = portfolioFacade.getHoldingsForUser(uid);
        List<StockHoldingDto> dtos = holdings.stream()
                .map(stockHoldingMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

}
