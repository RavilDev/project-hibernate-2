package org.example.safargulov.projecthibernate2.service;

import lombok.Getter;
import org.example.safargulov.projecthibernate2.dto.FilmActorDto;
import org.example.safargulov.projecthibernate2.entity.FilmActor;
import org.example.safargulov.projecthibernate2.repository.BaseRepository;

@Getter
public class FilmActorService {
    private final BaseRepository<FilmActor, Integer> repository;
    private final FilmService filmService;
    private final ActorService actorService;

    public FilmActorService(BaseRepository<FilmActor, Integer> repository, FilmService filmService, ActorService actorService) {
        this.repository = repository;
        this.filmService = filmService;
        this.actorService = actorService;
    }

    public FilmActorDto toDto(FilmActor filmActor) {
        return FilmActorDto.builder()
                .film(filmService.toDto(filmActor.getFilm()))
                .actor(actorService.toDto(filmActor.getActor()))
                .build();
    }

    public FilmActor toEntity(FilmActorDto filmActorDto) {
        return FilmActor.builder()
                .film(filmService.toEntity(filmActorDto.getFilm()))
                .actor(actorService.toEntity(filmActorDto.getActor()))
                .build();
    }


}
