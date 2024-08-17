package com.ing.stockexchange.v1.mapper;

import com.ing.stockexchange.v1.dto.StockDto;
import com.ing.stockexchange.v1.model.Stock;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-17T21:25:01+0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.1 (Homebrew)"
)
@Component
public class StockMapperImpl implements StockMapper {

    @Override
    public Stock toEntity(StockDto dto) {
        if ( dto == null ) {
            return null;
        }

        Stock.StockBuilder stock = Stock.builder();

        stock.name( dto.getName() );
        stock.description( dto.getDescription() );
        stock.currentPrice( dto.getCurrentPrice() );

        return stock.build();
    }

    @Override
    public List<Stock> toEntity(List<StockDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<Stock> list = new ArrayList<Stock>( dto.size() );
        for ( StockDto stockDto : dto ) {
            list.add( toEntity( stockDto ) );
        }

        return list;
    }

    @Override
    public StockDto toDto(Stock entity) {
        if ( entity == null ) {
            return null;
        }

        StockDto.StockDtoBuilder<?, ?> stockDto = StockDto.builder();

        stockDto.uuid( entity.getUuid() );
        stockDto.createdDate( entity.getCreatedDate() );
        stockDto.lastModifiedDate( entity.getLastModifiedDate() );
        stockDto.createdBy( entity.getCreatedBy() );
        stockDto.lastModifiedBy( entity.getLastModifiedBy() );
        stockDto.name( entity.getName() );
        stockDto.description( entity.getDescription() );
        stockDto.currentPrice( entity.getCurrentPrice() );

        return stockDto.build();
    }

    @Override
    public List<StockDto> toDto(List<Stock> entity) {
        if ( entity == null ) {
            return null;
        }

        List<StockDto> list = new ArrayList<StockDto>( entity.size() );
        for ( Stock stock : entity ) {
            list.add( toDto( stock ) );
        }

        return list;
    }
}
