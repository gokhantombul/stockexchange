package com.ing.stockexchange.v1.controller;

import com.ing.stockexchange.v1.dto.StockExchangeDto;
import com.ing.stockexchange.v1.mapper.StockExchangeMapper;
import com.ing.stockexchange.v1.model.StockExchange;
import com.ing.stockexchange.v1.service.StockExchangeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ing.stockexchange.v1.constant.UriConstant.STOCK_EXCHANGE_V1;

@RestController
@RequestMapping(STOCK_EXCHANGE_V1)
@RequiredArgsConstructor
public class StockExchangeController {

    private final StockExchangeService stockExchangeService;
    private final StockExchangeMapper stockExchangeMapper;

    @GetMapping("/{name}")
    public ResponseEntity<StockExchangeDto> getStockExchange(@PathVariable String name) {
        return ResponseEntity.ok(stockExchangeMapper.toDto(stockExchangeService.getStockExchange(name)));
    }

    @PatchMapping("/{name}")
    public ResponseEntity<Void> addStockToExchange(@PathVariable String name, @RequestParam String stockName) {
        stockExchangeMapper.toDto(stockExchangeService.addStockToExchange(name, stockName));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<StockExchangeDto> createStockExchange(@Valid @RequestBody StockExchangeDto stockExchangeDto) {
        StockExchange stockExchange = stockExchangeMapper.toEntity(stockExchangeDto);
        StockExchangeDto savedStockExchangeDto = stockExchangeMapper.toDto(stockExchangeService.createStockExchange(stockExchange));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStockExchangeDto);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteStockFromExchange(@PathVariable String name, @RequestParam String stockName) {
        stockExchangeService.deleteStockFromExchange(name, stockName);
        return ResponseEntity.ok().build();
    }

}

