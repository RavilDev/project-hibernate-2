package org.example.safargulov.projecthibernate2.service;

import lombok.Getter;
import org.example.safargulov.projecthibernate2.dto.FilmDto;
import org.example.safargulov.projecthibernate2.entity.Film;
import org.example.safargulov.projecthibernate2.entity.FilmActor;
import org.example.safargulov.projecthibernate2.entity.SpecialFeature;
import org.example.safargulov.projecthibernate2.repository.BaseRepository;

import java.util.List;
import java.util.Set;

@Getter
public class FilmService {
    private final BaseRepository<Film, Integer> repository;
    private final FilmActorService filmActorService;
    private final FilmCategoryService filmCategoryService;

    public FilmService(BaseRepository<Film, Integer> repository, FilmActorService filmActorService, FilmCategoryService filmCategoryService) {
        this.repository = repository;
        this.filmActorService = filmActorService;
        this.filmCategoryService = filmCategoryService;
    }

    public Film toEntity(FilmDto filmDto) {
        Set<SpecialFeature> specialFeatures = filmDto.getSpecialFeatures();
        List<FilmActor> filmActorList = filmDto.getFilmActors().stream().map(filmActorService::toEntity).toList();
        List<FilmCategory> filmCategoryList = filmDto.getFilmCategories().stream().map(filmCategoryService::toEntity).toList();
        return Film.builder()
                .description(filmDto.getDescription())
                .length(filmDto.getLength())
                .title(filmDto.getTitle())
                .specialFeatures(specialFeatures)
                .filmActor(filmActorList)
                // .filmCategory()
                .build();
    }

    public FilmDto toDto(Film film) {
        return FilmDto.builder()
                .description(film.getDescription())
                .title(film.getTitle())
                .length(film.getLength())
                .build();
    }
}
