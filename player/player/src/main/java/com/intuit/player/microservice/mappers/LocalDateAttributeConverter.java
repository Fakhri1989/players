package com.intuit.player.microservice.mappers;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, String> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String convertToDatabaseColumn(LocalDate attribute) {
        return (attribute != null) ? attribute.toString() : "";
    }

    @Override
    public LocalDate convertToEntityAttribute(String dbData) {
        return (dbData != null && !dbData.isEmpty()) ? LocalDate.parse(dbData) : null;
    }

}

