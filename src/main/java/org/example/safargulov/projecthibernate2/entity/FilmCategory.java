package org.example.safargulov.projecthibernate2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.safargulov.projecthibernate2.entity.id.FilmCategoryId;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "film_category")
@IdClass(value = FilmCategoryId.class)
public class FilmCategory {
    @Id
    @Column(name = "film_id", nullable = false)
    private Short filmId;

    @Id
    @Column(name = "category_id", nullable = false)
    private Byte categoryId;

    @Column(name = "last_update", insertable = false, updatable = false, nullable = false)
    private Timestamp lastUpdate;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
