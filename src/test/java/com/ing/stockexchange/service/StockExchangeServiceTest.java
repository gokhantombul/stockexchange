package com.ing.stockexchange.service;

import com.ing.stockexchange.v1.model.StockExchange;
import com.ing.stockexchange.v1.repository.StockExchangeRepository;
import com.ing.stockexchange.v1.service.StockExchangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class StockExchangeServiceTest {

    @InjectMocks
    private StockExchangeService stockExchangeService;

    @Mock
    private StockExchangeRepository stockExchangeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnStockExchangeWithLiveInMarketFalseWhenNewStockExchangeIsCreated() {
        // Given
        StockExchange newStockExchange = StockExchange.builder()
                .liveInMarket(true)
                .name("Sample Exchange")
                .description("Sample Description")
                .build();

        given(stockExchangeRepository.save(any(StockExchange.class))).willReturn(newStockExchange);

        // When
        StockExchange savedStockExchange = stockExchangeService.createStockExchange(newStockExchange);

        // Then
        assertThat(savedStockExchange.isLiveInMarket()).isFalse();
    }

    @Test
    void shouldReturnStockExchangeWhenStockExchangeExistsWithGivenName() {
        // Given
        String stockName = "Existing Stock Exchange";
        StockExchange existingStockExchange = StockExchange.builder()
                .name(stockName)
                .build();

        given(stockExchangeRepository.findByName(stockName)).willReturn(Optional.of(existingStockExchange));

        // When
        StockExchange result = stockExchangeService.getStockExchange(stockName);

        // Then
        assertThat(result.getName()).isEqualTo(stockName);
    }

    @Test
    void shouldThrowExceptionWhenStockExchangeDoesNotExistWithGivenName() {
        // Given
        String stockName = "Non-Existing Stock Exchange";
        given(stockExchangeRepository.findByName(stockName)).willReturn(Optional.empty());

        // When / Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> stockExchangeService.getStockExchange(stockName));
        assertThat(exception.getMessage()).isEqualTo("Stock Exchange not found");
    }
}
