package fsa.stocks.jpa.converter;

import fsa.stocks.domain.enums.UserRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole,String> {

    @Override
    public String convertToDatabaseColumn(UserRole role) {
        if (role == null) {
            return null;
        }
        return role.name();
    }

    @Override
    public UserRole convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return UserRole.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Invalid value for enum UserRole: " + dbData);
        }
    }
}
