package com.ing.stockexchange.v1.repository;

import com.ing.stockexchange.v1.model.Stock;
import com.ing.stockexchange.v1.model.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByName(String name);

    Optional<Stock> findByUuid(UUID uuid);

    @Modifying
    @Query(value = """
               UPDATE Stock_Exchange se
               SET se.LIVE_IN_MARKET = (
                   SELECT CASE WHEN COUNT(s.id) >= :stockSize THEN TRUE ELSE FALSE END
                   FROM Stock s
                   WHERE s.stock_exchange_id = se.id
               )
               WHERE se.id IN (
                   SELECT DISTINCT se.id
                   FROM Stock_Exchange se
                   LEFT JOIN Stock s ON s.stock_exchange_id = se.id
               );
            """, nativeQuery = true)
    void updateLivingStatus(int stockSize);

}
