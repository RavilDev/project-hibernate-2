package org.example.safargulov.projecthibernate2.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.safargulov.projecthibernate2.dto.CategoryDto;
import org.example.safargulov.projecthibernate2.dto.FilmCategoryDto;
import org.example.safargulov.projecthibernate2.entity.Category;
import org.example.safargulov.projecthibernate2.entity.FilmCategory;
import org.example.safargulov.projecthibernate2.repository.BaseRepository;

import java.util.List;

@Getter
@AllArgsConstructor
public class CategoryService {

    private final BaseRepository<Category, Integer> repository;
    private final FilmCategoryService filmCategoryService;

    public Category toEntity(CategoryDto categoryDto) {
        List<FilmCategory> filmCategoryList = categoryDto.getFilmCategories().stream().map(filmCategoryService::toEntity).toList();
        return Category.builder()
                .name(categoryDto.getName())
                .filmCategory(filmCategoryList)
                .build();
    }

    public CategoryDto toDto(Category category) {
        List<FilmCategoryDto> filmCategoryDtoList = category.getFilmCategory().stream().map(filmCategoryService::toDto).toList();
        return CategoryDto.builder()
                .name(category.getName())
                .filmCategories(filmCategoryDtoList)
                .build();
    }
}
