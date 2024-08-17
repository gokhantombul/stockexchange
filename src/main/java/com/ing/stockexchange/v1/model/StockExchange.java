package com.ing.stockexchange.v1.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ing.stockexchange.v1.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StockExchange extends BaseEntity {

    public StockExchange(Long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true, length = 256)
    private String name;

    @Column(nullable = false, length = 512)
    private String description;

    private boolean liveInMarket;

    @OneToMany(mappedBy = "stockExchange", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Stock> stocks;

}
