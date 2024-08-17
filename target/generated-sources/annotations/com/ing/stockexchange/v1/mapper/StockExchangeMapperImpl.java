package com.ing.stockexchange.v1.mapper;

import com.ing.stockexchange.v1.dto.StockDto;
import com.ing.stockexchange.v1.dto.StockExchangeDto;
import com.ing.stockexchange.v1.model.Stock;
import com.ing.stockexchange.v1.model.StockExchange;
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
public class StockExchangeMapperImpl implements StockExchangeMapper {

    @Override
    public StockExchange toEntity(StockExchangeDto dto) {
        if ( dto == null ) {
            return null;
        }

        StockExchange.StockExchangeBuilder stockExchange = StockExchange.builder();

        stockExchange.name( dto.getName() );
        stockExchange.description( dto.getDescription() );
        stockExchange.liveInMarket( dto.isLiveInMarket() );
        stockExchange.stocks( stockDtoListToStockList( dto.getStocks() ) );

        return stockExchange.build();
    }

    @Override
    public List<StockExchange> toEntity(List<StockExchangeDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<StockExchange> list = new ArrayList<StockExchange>( dto.size() );
        for ( StockExchangeDto stockExchangeDto : dto ) {
            list.add( toEntity( stockExchangeDto ) );
        }

        return list;
    }

    @Override
    public StockExchangeDto toDto(StockExchange entity) {
        if ( entity == null ) {
            return null;
        }

        StockExchangeDto.StockExchangeDtoBuilder<?, ?> stockExchangeDto = StockExchangeDto.builder();

        stockExchangeDto.uuid( entity.getUuid() );
        stockExchangeDto.createdDate( entity.getCreatedDate() );
        stockExchangeDto.lastModifiedDate( entity.getLastModifiedDate() );
        stockExchangeDto.createdBy( entity.getCreatedBy() );
        stockExchangeDto.lastModifiedBy( entity.getLastModifiedBy() );
        stockExchangeDto.name( entity.getName() );
        stockExchangeDto.description( entity.getDescription() );
        stockExchangeDto.liveInMarket( entity.isLiveInMarket() );
        stockExchangeDto.stocks( stockListToStockDtoList( entity.getStocks() ) );

        return stockExchangeDto.build();
    }

    @Override
    public List<StockExchangeDto> toDto(List<StockExchange> entity) {
        if ( entity == null ) {
            return null;
        }

        List<StockExchangeDto> list = new ArrayList<StockExchangeDto>( entity.size() );
        for ( StockExchange stockExchange : entity ) {
            list.add( toDto( stockExchange ) );
        }

        return list;
    }

    protected Stock stockDtoToStock(StockDto stockDto) {
        if ( stockDto == null ) {
            return null;
        }

        Stock.StockBuilder stock = Stock.builder();

        stock.name( stockDto.getName() );
        stock.description( stockDto.getDescription() );
        stock.currentPrice( stockDto.getCurrentPrice() );

        return stock.build();
    }

    protected List<Stock> stockDtoListToStockList(List<StockDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Stock> list1 = new ArrayList<Stock>( list.size() );
        for ( StockDto stockDto : list ) {
            list1.add( stockDtoToStock( stockDto ) );
        }

        return list1;
    }

    protected StockDto stockToStockDto(Stock stock) {
        if ( stock == null ) {
            return null;
        }

        StockDto.StockDtoBuilder<?, ?> stockDto = StockDto.builder();

        stockDto.uuid( stock.getUuid() );
        stockDto.createdDate( stock.getCreatedDate() );
        stockDto.lastModifiedDate( stock.getLastModifiedDate() );
        stockDto.createdBy( stock.getCreatedBy() );
        stockDto.lastModifiedBy( stock.getLastModifiedBy() );
        stockDto.name( stock.getName() );
        stockDto.description( stock.getDescription() );
        stockDto.currentPrice( stock.getCurrentPrice() );

        return stockDto.build();
    }

    protected List<StockDto> stockListToStockDtoList(List<Stock> list) {
        if ( list == null ) {
            return null;
        }

        List<StockDto> list1 = new ArrayList<StockDto>( list.size() );
        for ( Stock stock : list ) {
            list1.add( stockToStockDto( stock ) );
        }

        return list1;
    }
}
