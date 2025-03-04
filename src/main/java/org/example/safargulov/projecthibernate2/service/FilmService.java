package org.example.safargulov.projecthibernate2.service;

import lombok.Getter;
import org.example.safargulov.projecthibernate2.config.SessionCreator;
import org.example.safargulov.projecthibernate2.dto.FilmActorDto;
import org.example.safargulov.projecthibernate2.dto.FilmCategoryDto;
import org.example.safargulov.projecthibernate2.dto.FilmDto;
import org.example.safargulov.projecthibernate2.entity.*;
import org.example.safargulov.projecthibernate2.repository.BaseRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

@Getter
public class FilmService {
    private final BaseRepository<Film, Integer> repository;
    private final FilmActorService filmActorService;
    private final FilmCategoryService filmCategoryService;
    private final LanguageService languageService;
    private final StoreService storeService;

    public FilmService(BaseRepository<Film, Integer> repository, FilmActorService filmActorService, FilmCategoryService filmCategoryService, LanguageService languageService, StoreService storeService) {
        this.repository = repository;
        this.filmActorService = filmActorService;
        this.filmCategoryService = filmCategoryService;
        this.languageService = languageService;
        this.storeService = storeService;
    }

    public Film toEntity(FilmDto filmDto) {
        List<FilmActor> filmActorList = filmDto.getFilmActors().stream().map(filmActorService::toEntity).toList();
        List<FilmCategory> filmCategoryList = filmDto.getFilmCategories().stream().map(filmCategoryService::toEntity).toList();
        return Film.builder()
                .description(filmDto.getDescription())
                .length(filmDto.getLength())
                .title(filmDto.getTitle())
                .specialFeatures(filmDto.getSpecialFeatures())
                .filmActor(filmActorList)
                .filmCategory(filmCategoryList)
                .language(languageService.toEntity(filmDto.getLanguage()))
                .originalLanguage(languageService.toEntity(filmDto.getOriginalLanguage()))
                .build();
    }

    public FilmDto toDto(Film film) {
        List<FilmActorDto> filmActorDtoList = film.getFilmActor().stream().map(filmActorService::toDto).toList();
        List< FilmCategoryDto> filmCategoryDtoList = film.getFilmCategory().stream().map(filmCategoryService::toDto).toList();
        return FilmDto.builder()
                .description(film.getDescription())
                .title(film.getTitle())
                .length(film.getLength())
                .specialFeatures(film.getSpecialFeatures())
                .filmActors(filmActorDtoList)
                .filmCategories(filmCategoryDtoList)
                .language(languageService.toDto(film.getLanguage()))
                .originalLanguage(languageService.toDto(film.getOriginalLanguage()))
                .build();
    }

    public FilmDto createRentableFilm(FilmDto filmDto, Integer storeId) throws Exception {
        if (filmDto == null || filmDto.getTitle() == null || filmDto.getLanguage() == null) {
            throw new IllegalArgumentException("Film data is incomplete");
        }
        SessionCreator creator = new SessionCreator();
        Transaction transaction = null;
        try (Session session = creator.getSession()) {
            transaction = session.beginTransaction();
            Language language = languageService.toEntity(filmDto.getLanguage());
            List<FilmActor> filmActor = filmDto.getFilmActors().stream().map(filmActorService::toEntity).toList();
            List<FilmCategory> filmCategory = filmDto.getFilmCategories().stream().map(filmCategoryService::toEntity).toList();
            Optional<Store> storeOpt = storeService.find(storeId);
            if (storeOpt.isEmpty()) {
                throw new IllegalArgumentException("Store not found");
            }
            Film film = Film.builder()
                    .description(filmDto.getDescription())
                    .length(filmDto.getLength())
                    .title(filmDto.getTitle())
                    .specialFeatures(filmDto.getSpecialFeatures())
                    .filmActor(filmActor)
                    .filmCategory(filmCategory)
                    .language(language)
                    .originalLanguage(filmDto.getOriginalLanguage() != null ? languageService.toEntity(filmDto.getOriginalLanguage()) : null)
                    .build();

            filmActor.forEach(fa -> fa.setFilm(film));
            filmCategory.forEach(fc -> fc.setFilm(film));

            Store store = storeOpt.get();
            Inventory inventory = Inventory.builder()
                    .store(store)
                    .film(film)
                    .build();

            session.persist(film);
            session.persist(inventory);
            transaction.commit();
            return toDto(film);
        } catch (Exception e) {
            if (transaction == null) throw new AssertionError();
            transaction.rollback();
            throw e;
        } finally {
            creator.close();
        }
    }
}
