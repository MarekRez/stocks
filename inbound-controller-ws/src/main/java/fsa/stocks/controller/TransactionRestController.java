//package fsa.stocks.controller;
//
//import fsa.stocks.domain.Stock;
//import fsa.stocks.domain.Transaction;
//import fsa.stocks.domain.TransactionFactory;
//import fsa.stocks.domain.User;
//import fsa.stocks.domain.enums.TransactionType;
//import fsa.stocks.domain.service.StockFacade;
//import fsa.stocks.domain.service.TransactionFacade;
//import fsa.stocks.mapper.TransactionMapper;
//import fsa.stocks.rest.api.TransactionsApi;
//import fsa.stocks.rest.dto.TransactionDto;
//import fsa.stocks.rest.dto.TransactionTypeDto;
//import fsa.stocks.security.oauth2_jwt.CurrentUserDetailService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.math.BigDecimal;
//import java.util.Optional;
//
//@RestController
//public class TransactionRestController implements TransactionsApi {
//
//    private final TransactionFacade transactionFacade;
//    private final TransactionFactory transactionFactory;
//    private final CurrentUserDetailService currentUserDetailService;
//    private final TransactionMapper transactionMapper;
//    private final StockFacade stockFacade;
//
//    public TransactionRestController(TransactionFacade transactionFacade,
//                                      TransactionFactory transactionFactory,
//                                      CurrentUserDetailService currentUserDetailService,
//                                      TransactionMapper transactionMapper,
//                                     StockFacade stockFacade) {
//        this.transactionFacade = transactionFacade;
//        this.transactionFactory = transactionFactory;
//        this.currentUserDetailService = currentUserDetailService;
//        this.transactionMapper = transactionMapper;
//        this.stockFacade = stockFacade;
//    }
//
//    @Override
//    public ResponseEntity<Void> createTransaction(TransactionDto transactionDto) {
//        Optional<User> optionalUser = currentUserDetailService.getFullCurrentUser();
//        if (optionalUser.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//        User user = optionalUser.get();
//
//        // Convert TransactionTypeDto to domain TransactionType
//        TransactionTypeDto dtoType = transactionDto.getType();
//        TransactionType type;
//        try {
//            type = TransactionType.valueOf(dtoType.name());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        // Convert the amount from Double to BigDecimal
//        BigDecimal amount = BigDecimal.valueOf(transactionDto.getAmount());
//
//        // For BUY or SELL transactions, ensure that a StockDto is provided and retrieve the corresponding Stock entity.
//        Stock stock = null;
//        if (dtoType == TransactionTypeDto.BUY || dtoType == TransactionTypeDto.SELL) {
//            if (transactionDto.getStock() == null || transactionDto.getStock().getSymbol() == null) {
//                return ResponseEntity.badRequest().build();
//            }
//            stock = stockFacade.get(transactionDto.getStock().getSymbol());
//            if (stock == null) {
//                return ResponseEntity.badRequest().build();
//            }
//        }
//
//        try {
//            Transaction transaction = transactionFactory.create(user, type, amount, stock);
//            transactionFacade.recordTransaction(transaction);
//            return ResponseEntity.accepted().build();
//        } catch (Exception e) {
//            // Optionally log the exception details for debugging.
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @Override
//    public ResponseEntity<List<TransactionDto>> listTransactions() {
//        Optional<User> optionalUser = currentUserDetailService.getFullCurrentUser();
//        if (optionalUser.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//        User user = optionalUser.get();
//
//        List<Transaction> transactions = transactionService.findByUser(user);
//        List<TransactionDto> transactionDtos = transactions.stream()
//                .map(transactionMapper::toDto)
//                .toList();
//        return ResponseEntity.ok(transactionDtos);
//    }
//}
