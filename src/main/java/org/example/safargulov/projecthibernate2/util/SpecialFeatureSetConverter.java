package org.example.safargulov.projecthibernate2.util;

import jakarta.persistence.AttributeConverter;
import org.example.safargulov.projecthibernate2.entity.SpecialFeature;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SpecialFeatureSetConverter implements AttributeConverter<Set<SpecialFeature>, String> {

    public static final String SEPARATOR = ",";


    @Override
    public String convertToDatabaseColumn(Set<SpecialFeature> specialFeatures) {
        if (specialFeatures == null || specialFeatures.isEmpty()) {
            return "";
        }
        return specialFeatures.stream()
                .map(this::formatFeature)
                .collect(Collectors.joining(SEPARATOR));
    }

    @Override
    public Set<SpecialFeature> convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty()) {
            return Set.of();
        }
        return Arrays.stream(s.split(SEPARATOR))
                .map(String::trim)
                .map(SpecialFeature::valueOf)
                .collect(Collectors.toSet());
    }

    private String formatFeature(SpecialFeature feature) {
        return switch (feature) {
            case TRAILERS -> "Trailers";
            case COMMENTARIES -> "Commentaries";
            case DELETED_SCENES -> "Deleted Scenes";
            case BEHIND_THE_SCENES -> "Behind the Scenes";
        };
    }
}
