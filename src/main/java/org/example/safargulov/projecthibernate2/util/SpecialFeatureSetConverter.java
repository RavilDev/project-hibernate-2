package org.example.safargulov.projecthibernate2.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.safargulov.projecthibernate2.entity.SpecialFeature;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class SpecialFeatureSetConverter implements AttributeConverter<Set<SpecialFeature>, String> {
    public static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(Set<SpecialFeature> specialFeatures) {
        if (specialFeatures == null || specialFeatures.isEmpty()) {
            return "";
        }
        return specialFeatures.stream()
                .map(feature -> feature.name().substring(0, 1) + feature.name().substring(1).toLowerCase())
                .collect(Collectors.joining(SEPARATOR));
    }

    @Override
    public Set<SpecialFeature> convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty()) {
            return Set.of();
        }
        return Arrays.stream(s.split(SEPARATOR))
                .map(String::trim)
                .map(String::toUpperCase)
                .map(SpecialFeature::valueOf)
                .collect(Collectors.toSet());
    }
}
