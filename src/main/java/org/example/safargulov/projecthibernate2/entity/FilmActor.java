package org.example.safargulov.projecthibernate2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.safargulov.projecthibernate2.entity.id.FilmActorId;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "film_actor")
@IdClass(value = FilmActorId.class)
public class FilmActor {
    @Id
    @Column(name = "actor_id", nullable = false)
    private Long actorId;

    @Id
    @Column(name = "film_id", nullable = false)
    private Long filmId;

    @Column(name = "last_update", insertable = false, updatable = false, nullable = false)
    private Timestamp lastUpdate;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "actor_id", nullable = false)
    private Actor actor;

}
