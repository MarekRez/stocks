package fsa.stocks.controller;

import fsa.stocks.domain.Stock;
import fsa.stocks.domain.enums.StockSymbol;
import fsa.stocks.domain.service.StockFacade;
import fsa.stocks.rest.api.StocksApi;
import fsa.stocks.rest.dto.StockDto;
import fsa.stocks.mapper.StockMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class StockRestController implements StocksApi {

    private static final Logger log = LoggerFactory.getLogger(StockRestController.class);

    private final StockFacade stockFacade;
    private final StockMapper stockMapper;

    public StockRestController(StockFacade stockFacade, StockMapper stockMapper) {
        this.stockFacade = stockFacade;
        this.stockMapper = stockMapper;
    }

    @Override
    public ResponseEntity<List<StockDto>> listStocks() {
        List<Stock> stocks = stockFacade.listAll();
        List<StockDto> dtos = stocks.stream().map(stockMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
        //or return allDiscussionMessages != null ?
        //                ResponseEntity.ok().body(allDiscussionMessages.stream().map(discussionMessageMapper::toDto).toList()) :
        //                ResponseEntity.ok().body(Collections.emptyList());
    }

    @Override
    @Secured("ROLE_ADMIN")
    public ResponseEntity<StockDto> createStock(StockDto stockDto) {
        Stock stock = stockMapper.toEntity(stockDto);
        log.debug("Mapped Stock entity currency: {}", stock.getCurrency());
        stockFacade.create(stock);
        StockDto response = stockMapper.toDto(stock);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<StockDto> getStockBySymbol(String symbol) {
        StockSymbol stockSymbol;
        try {
            stockSymbol = StockSymbol.valueOf(symbol.toUpperCase());
        } catch (IllegalArgumentException e) {
            // If the symbol does not match any enum value, return a bad request
            return ResponseEntity.badRequest().build();
        }

        Optional<Stock> stockOptional = stockFacade.get(stockSymbol);
        return stockOptional
                .map(stock -> ResponseEntity.ok(stockMapper.toDto(stock)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
