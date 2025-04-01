package fsa.stocks.jpa.converter;

import fsa.stocks.domain.enums.StockSymbol;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StockSymbolConverter implements AttributeConverter<StockSymbol,String> {

    @Override
    public String convertToDatabaseColumn(StockSymbol symbol) {
        if (symbol == null) {
            return null;
        }
        return symbol.name();
    }

    @Override
    public StockSymbol convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return StockSymbol.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Invalid value for enum StockSymbol: " + dbData);
        }
    }
}
