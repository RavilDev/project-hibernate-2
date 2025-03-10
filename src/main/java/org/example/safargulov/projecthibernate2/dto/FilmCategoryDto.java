package org.example.safargulov.projecthibernate2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmCategoryDto {
    private CategoryDto category;
    private FilmDto film;
}
