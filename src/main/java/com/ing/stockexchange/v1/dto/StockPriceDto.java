package com.ing.stockexchange.v1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
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
public class StockPriceDto {

    @NotNull(message = "The current price cannot be null")
    @DecimalMin(value = "0", inclusive = false, message = "The current price must be greater than 0")
    @Digits(integer = 20, fraction = 10, message = "The current price can have 20 digits and 10 fragment")
    private BigDecimal currentPrice;

    @NotBlank(message = "The name must not be blank")
    @Size(min = 2, max = 512, message = "The name must be between 2 and 512 characters.")
    private String name;

}
