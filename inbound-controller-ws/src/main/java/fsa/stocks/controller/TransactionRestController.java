package fsa.stocks.controller;

import fsa.stocks.domain.User;
import fsa.stocks.domain.service.TransactionFacade;
import fsa.stocks.mapper.TransactionMapper;
import fsa.stocks.rest.api.TransactionsApi;
import fsa.stocks.rest.dto.TransactionDto;
import fsa.stocks.security.oauth2_jwt.CurrentUserDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TransactionRestController implements TransactionsApi {

    private final TransactionMapper transactionMapper;
    private final TransactionFacade transactionFacade;
    private final CurrentUserDetailService currentUserDetailService;

    public TransactionRestController(TransactionMapper transactionMapper,
                                     TransactionFacade transactionFacade,
                                     CurrentUserDetailService currentUserDetailService) {
        this.transactionMapper = transactionMapper;
        this.transactionFacade = transactionFacade;
        this.currentUserDetailService = currentUserDetailService;
    }

    @Override
    public ResponseEntity<List<TransactionDto>> listTransactions() {
        User user = currentUserDetailService.getFullCurrentUser();
        List<TransactionDto> dtos = transactionFacade
                .findByUser(user)
                .stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}
