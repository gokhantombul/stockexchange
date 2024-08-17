package com.ing.stockexchange.v1.controller;

import com.ing.stockexchange.v1.dto.StockDto;
import com.ing.stockexchange.v1.dto.StockPriceDto;
import com.ing.stockexchange.v1.mapper.StockMapper;
import com.ing.stockexchange.v1.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.ing.stockexchange.v1.constant.UriConstant.STOCK_V1;

@RestController
@RequestMapping(STOCK_V1)
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    private final StockMapper stockMapper;

    @PostMapping
    public ResponseEntity<StockDto> createStock(@RequestBody @Valid StockDto stockDto) {
        return new ResponseEntity<>(stockMapper.toDto(stockService.create(stockMapper.toEntity(stockDto))), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Void> updateStockPrice(@Valid @RequestBody StockPriceDto stockPriceDto) {
        stockMapper.toDto(stockService.updatePrice(stockPriceDto));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{stockUuid}")
    public ResponseEntity<Void> deleteStock(@PathVariable UUID stockUuid) {
        stockService.deleteStock(stockUuid);
        return ResponseEntity.ok().build();
    }

}
