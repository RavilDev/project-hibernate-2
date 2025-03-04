package org.example.safargulov.projecthibernate2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "film_text")
public class FilmText {
    @Id
    @Column(name = "film_id", nullable = false)
    private Short filmId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "film_id", nullable = false)
    @MapsId
    private Film film;

}
