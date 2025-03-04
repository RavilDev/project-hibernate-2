package org.example.safargulov.projecthibernate2.service;

import lombok.Getter;
import org.example.safargulov.projecthibernate2.dto.ActorDto;
import org.example.safargulov.projecthibernate2.dto.FilmActorDto;
import org.example.safargulov.projecthibernate2.entity.Actor;
import org.example.safargulov.projecthibernate2.entity.FilmActor;
import org.example.safargulov.projecthibernate2.repository.BaseRepository;

import java.util.List;

@Getter
public class ActorService {
    private final BaseRepository<Actor, Integer> repository;
    private final FilmActorService filmActorService;

    public ActorService(BaseRepository<Actor, Integer> repository, FilmActorService filmActorService) {
        this.repository = repository;
        this.filmActorService = filmActorService;
    }

    public ActorDto toDto(Actor actor) {
        List<FilmActorDto> filmActorDtoList = actor.getFilmActor().stream().map(filmActorService::toDto).toList();
        return ActorDto.builder()
                .firstName(actor.getFirstName())
                .lastName(actor.getLastName())
                .filmActor(filmActorDtoList)
                .build();
    }

    public Actor toEntity(ActorDto actorDto) {
        List<FilmActor> filmActorList = actorDto.getFilmActor().stream().map(filmActorService::toEntity).toList();
        return Actor.builder()
                .firstName(actorDto.getFirstName())
                .lastName(actorDto.getLastName())
                .filmActor(filmActorList)
                .build();
    }

}
