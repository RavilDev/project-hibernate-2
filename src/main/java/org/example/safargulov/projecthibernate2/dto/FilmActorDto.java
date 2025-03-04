package org.example.safargulov.projecthibernate2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmActorDto {
    private ActorDto actor;
    private FilmDto film;
}
