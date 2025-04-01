package fsa.stocks.jpa.converter;

import fsa.stocks.domain.enums.TransactionType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TransactionTypeConverter implements AttributeConverter<TransactionType,String> {

    @Override
    public String convertToDatabaseColumn(TransactionType type) {
        if (type == null) {
            return null;
        }
        return type.name();
    }

    @Override
    public TransactionType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return TransactionType.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Invalid value for enum TransactionType: " + dbData);
        }
    }
}
