package com.ing.stockexchange.v1.mapper;

import com.ing.stockexchange.v1.dto.StockDto;
import com.ing.stockexchange.v1.mapper.base.BaseMapper;
import com.ing.stockexchange.v1.model.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper extends BaseMapper<Stock, StockDto> {

}
