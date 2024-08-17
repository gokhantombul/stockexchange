package com.ing.stockexchange.v1.mapper;

import com.ing.stockexchange.v1.dto.StockExchangeDto;
import com.ing.stockexchange.v1.mapper.base.BaseMapper;
import com.ing.stockexchange.v1.model.StockExchange;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockExchangeMapper extends BaseMapper<StockExchange, StockExchangeDto> {

}
