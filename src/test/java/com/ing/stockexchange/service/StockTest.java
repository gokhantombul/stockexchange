package com.ing.stockexchange.service;

import com.ing.stockexchange.v1.exception.ApiRequestException;
import com.ing.stockexchange.v1.model.Stock;
import com.ing.stockexchange.v1.repository.StockRepository;
import com.ing.stockexchange.v1.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class StockTest {

    private static final int MIN_STOCK = 5;
    private static final String STOCK_NAME = "GOOGL";

    @InjectMocks
    private StockService stockService;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveStock_shouldSaveStock() {
        // Given
        Stock stock = Stock.builder().name(STOCK_NAME).build();
        when(stockRepository.save(stock)).thenReturn(stock);

        // When
        stockService.save(stock);

        // Then
        verify(stockRepository, times(1)).save(stock);
    }

    @Test
    void findStockByName_whenExists_shouldReturnStock() {
        // Given
        Stock stock = Stock.builder().name(STOCK_NAME).description("Tech company").build();
        when(stockRepository.findByName(STOCK_NAME)).thenReturn(Optional.of(stock));

        // When
        Stock foundStock = stockService.findByName(STOCK_NAME);

        // Then
        assertEquals(STOCK_NAME, foundStock.getName());
    }

    @Test
    void findStockByName_whenNotExists_shouldThrowException() {
        // Given
        String stockName = "AAPL";
        when(stockRepository.findByName(stockName)).thenReturn(Optional.empty());

        // When & Then
        ApiRequestException thrown = assertThrows(ApiRequestException.class, () -> stockService.findByName(stockName));
        assertEquals("Stock not found", thrown.getMessage());
    }

    @Test
    void deleteStock_whenExists_shouldDeleteAndUpdateStatus() {
        // Given
        Stock stock = Stock.builder().name(STOCK_NAME).build();
        when(stockRepository.findByName(STOCK_NAME)).thenReturn(Optional.of(stock));
        doNothing().when(stockRepository).delete(stock);
        doNothing().when(stockRepository).updateLivingStatus(MIN_STOCK);

        // When
        stockService.deleteStock(STOCK_NAME);

        // Then
        verify(stockRepository).delete(stock);
    }

    @Test
    void updateStockPrice_shouldSaveUpdatedPrice() {
        // Given
        BigDecimal newPrice = BigDecimal.valueOf(150.00);
        Stock stock = Stock.builder().name(STOCK_NAME).currentPrice(newPrice).build();
        when(stockRepository.findByName(STOCK_NAME)).thenReturn(Optional.of(stock));
        when(stockRepository.save(stock)).thenReturn(stock);

        // When
        stockService.updatePrice(STOCK_NAME, newPrice);

        // Then
        verify(stockRepository).save(stock);
    }

}
