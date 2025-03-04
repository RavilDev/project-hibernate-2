package org.example.safargulov.projecthibernate2.entity.id;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmCategoryId {
    private Long filmId;
    private Long categoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmCategoryId that = (FilmCategoryId) o;
        return Objects.equals(filmId, that.filmId) && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, categoryId);
    }
}
