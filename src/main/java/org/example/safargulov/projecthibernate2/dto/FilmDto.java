package org.example.safargulov.projecthibernate2.dto;

import lombok.*;
import org.example.safargulov.projecthibernate2.entity.SpecialFeature;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmDto {
    private String title;
    private String description;
    private LocalDateTime releaseYear;
    private Integer length;
    private Set<SpecialFeature> specialFeatures;
    private LanguageDto language;
    private LanguageDto originalLanguage;
    private List<FilmActorDto> filmActors;
    private List<FilmCategoryDto> filmCategories;
}
