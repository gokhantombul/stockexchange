package com.ing.stockexchange.v1.service;

import com.ing.stockexchange.v1.dto.StockPriceDto;
import com.ing.stockexchange.v1.exception.ApiRequestException;
import com.ing.stockexchange.v1.model.Stock;
import com.ing.stockexchange.v1.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    @Value("${stock.min.number}")
    private int minStock;

    public Stock updatePrice(StockPriceDto stockPriceDto) {
        Stock stock = findByName(stockPriceDto.getName());
        stock.setCurrentPrice(stockPriceDto.getCurrentPrice());
        return save(stock);
    }

    public void updatePrice(String name, BigDecimal price) {
        Stock stock = findByName(name);
        stock.setCurrentPrice(price);
        save(stock);
    }

    public Stock create(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock findByUuid(UUID stockUuid) {
        return stockRepository.findByUuid(stockUuid).orElseThrow(() -> new ApiRequestException("Stock not found"));
    }

    public Stock findByName(String name) {
        return stockRepository.findByName(name).orElseThrow(() -> new ApiRequestException("Stock not found"));
    }

    @Transactional
    public void deleteStock(String name) {
        Stock stock = findByName(name);
        stockRepository.delete(stock);
        stockRepository.updateLivingStatus(minStock);
    }

    @Transactional
    public void deleteStock(UUID stockUuid) {
        Stock stock = findByUuid(stockUuid);
        stockRepository.delete(stock);
        stockRepository.updateLivingStatus(minStock);
    }

}
