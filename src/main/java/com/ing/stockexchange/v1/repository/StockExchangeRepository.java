package com.ing.stockexchange.v1.repository;

import com.ing.stockexchange.v1.model.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockExchangeRepository extends JpaRepository<StockExchange, Long> {

    Optional<StockExchange> findByName(String name);

}
