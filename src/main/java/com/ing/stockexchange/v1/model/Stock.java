package com.ing.stockexchange.v1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ing.stockexchange.v1.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Stock extends BaseEntity {

    @Column(nullable = false, unique = true, length = 256)
    private String name;

    @Column(nullable = false, length = 512)
    private String description;

    private BigDecimal currentPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_exchange_id")
    @JsonBackReference
    private StockExchange stockExchange;

}
