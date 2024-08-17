package com.ing.stockexchange.v1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ing.stockexchange.v1.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class StockDto extends BaseDto {

    private String name;
    private String description;
    private BigDecimal currentPrice;

}
