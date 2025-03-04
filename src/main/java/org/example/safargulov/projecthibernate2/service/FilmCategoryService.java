package org.example.safargulov.projecthibernate2.service;

import lombok.Getter;
import org.example.safargulov.projecthibernate2.dto.FilmCategoryDto;
import org.example.safargulov.projecthibernate2.entity.FilmCategory;
import org.example.safargulov.projecthibernate2.repository.BaseRepository;

@Getter
public class FilmCategoryService {
    private final BaseRepository<FilmCategory, Integer> filmCategoryRepository;
    private final FilmService filmService;
    private final CategoryService categoryService;

    public FilmCategoryService(BaseRepository<FilmCategory, Integer> filmCategoryRepository, FilmService filmService, CategoryService categoryService) {
        this.filmCategoryRepository = filmCategoryRepository;
        this.filmService = filmService;
        this.categoryService = categoryService;
    }

    public FilmCategoryDto toDto(FilmCategory filmCategory) {
        return FilmCategoryDto.builder()
                .film(filmService.toDto(filmCategory.getFilm()))
                .category(categoryService.toDto(filmCategory.getCategory()))
                .build();
    }

    public FilmCategory toEntity(FilmCategoryDto filmCategoryDto) {
        return FilmCategory.builder()
                .film(filmService.toEntity(filmCategoryDto.getFilm()))
                .category(categoryService.toEntity(filmCategoryDto.getCategory()))
                .build();
    }
}
