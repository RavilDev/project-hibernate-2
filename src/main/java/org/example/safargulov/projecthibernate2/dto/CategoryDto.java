package org.example.safargulov.projecthibernate2.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    private String name;
    private List<FilmCategoryDto> filmCategories;
}
