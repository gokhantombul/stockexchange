package com.ing.stockexchange.v1.initialize;

import com.ing.stockexchange.v1.auth.RegisterRequest;
import com.ing.stockexchange.v1.model.Stock;
import com.ing.stockexchange.v1.model.StockExchange;
import com.ing.stockexchange.v1.model.user.Role;
import com.ing.stockexchange.v1.repository.StockExchangeRepository;
import com.ing.stockexchange.v1.repository.StockRepository;
import com.ing.stockexchange.v1.service.user.AuthenticationService;
import com.ing.stockexchange.v1.service.user.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final StockExchangeRepository stockExchangeRepository;
    private final StockRepository stockRepository;
    private final RoleService roleService;
    private final AuthenticationService authenticationService;

    @Override
    public void run(ApplicationArguments args) {
        createRoles();
        createUser();
        createStockExchanges();
        createStocks();
    }

    private void createUser() {
        authenticationService.register(RegisterRequest.builder()
                .name("Gökhan")
                .surname("Tombul")
                .email("gokhantombul@hotmail.com")
                .password("1234")
                .roleList(Arrays.asList(roleService.findByName("ROLE_ADMIN"), roleService.findByName("ROLE_USER")))
                .build());
    }

    private void createRoles() {
        roleService.save(new Role("ROLE_ADMIN"));
        roleService.save(new Role("ROLE_USER"));
    }

    private void createStockExchanges() {
        List<StockExchange> stockExchangeList = new ArrayList<>();
        stockExchangeList.add(StockExchange.builder().name("Borsa İstanbul").description("Türkiye's largest stock exchange").liveInMarket(false).build());
        stockExchangeList.add(StockExchange.builder().name("New York Stock Exchange").description("The world's largest stock exchange").liveInMarket(false).build());
        stockExchangeList.add(StockExchange.builder().name("Tokyo Stock Exchange").description("Japan's largest stock exchange").liveInMarket(false).build());
        stockExchangeRepository.saveAll(stockExchangeList);
    }

    private void createStocks() {
        List<Stock> stockList = new ArrayList<>();
        stockList.add(Stock.builder().name("THYAO").description("Türk Hava Yolları").currentPrice(new BigDecimal("20.5")).stockExchange(new StockExchange(1L)).build());
        stockList.add(Stock.builder().name("GARAN").description("Garanti Bankası").currentPrice(new BigDecimal("12.3")).stockExchange(new StockExchange(1L)).build());
        stockList.add(Stock.builder().name("AAPL").description("Apple Inc.").currentPrice(new BigDecimal("180.7")).stockExchange(new StockExchange(2L)).build());
        stockList.add(Stock.builder().name("GOOGL").description("Alphabet Inc.").currentPrice(new BigDecimal("2400.6")).stockExchange(new StockExchange(2L)).build());
        stockList.add(Stock.builder().name("SONY").description("Sony Corporation").currentPrice(new BigDecimal("112.5")).stockExchange(new StockExchange(3L)).build());
        stockList.add(Stock.builder().name("TOYOF").description("Toyota Motor Corporation").currentPrice(new BigDecimal("85.7")).stockExchange(new StockExchange(3L)).build());
        stockRepository.saveAll(stockList);
    }

}
