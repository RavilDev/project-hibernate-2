package org.example.safargulov.projecthibernate2.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorDto {
    private String firstName;
    private String lastName;
    private List<FilmActorDto> filmActor;

}
