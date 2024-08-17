package com.ing.stockexchange.v1.service;

import com.ing.stockexchange.v1.model.Stock;
import com.ing.stockexchange.v1.model.StockExchange;
import com.ing.stockexchange.v1.repository.StockExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockExchangeService {

    private final StockExchangeRepository stockExchangeRepository;
    private final StockService stockService;

    @Value("${stock.min.number}")
    private int minStock;

    public StockExchange addStockToExchange(String exchangeName, String stockName) {
        StockExchange stockExchange = getStockExchange(exchangeName);

        Stock stock = stockService.findByName(stockName);
        stock.setStockExchange(stockExchange);
        updateLiveInMarketStatus(stockExchange);
        return stockExchangeRepository.save(stockExchange);
    }

    public StockExchange getStockExchange(String exchangeName) {
        return stockExchangeRepository.findByName(exchangeName).orElseThrow(() -> new RuntimeException("Stock Exchange not found"));
    }

    private void updateLiveInMarketStatus(StockExchange stockExchange) {
        List<Stock> stockExchangeList = stockExchange.getStocks();
        stockExchange.setLiveInMarket(!stockExchangeList.isEmpty() && stockExchangeList.size() >= minStock);
    }

    public void deleteStockFromExchange(String exchangeName, String stockName) {
        StockExchange stockExchange = getStockExchange(exchangeName);
        Stock stock = stockService.findByName(stockName);
        boolean stockAvailable = stockExchange.getStocks().stream().anyMatch(se -> se.getUuid().equals(stock.getUuid()));
        if (stockAvailable) {
            stock.setStockExchange(null);
            stockService.save(stock);
        } else {
            throw new RuntimeException("Stock not found in Stock Exchange");
        }
    }

    public StockExchange createStockExchange(StockExchange newStockExchange) {
        newStockExchange.setLiveInMarket(false);
        return stockExchangeRepository.save(newStockExchange);
    }

}
