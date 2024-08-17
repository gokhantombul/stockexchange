package com.ing.stockexchange.v1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ing.stockexchange.v1.dto.base.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class StockExchangeDto extends BaseDto {

    private String name;
    private String description;
    private boolean liveInMarket;
    private List<StockDto> stocks;

}
