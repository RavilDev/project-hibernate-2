package org.example.safargulov.projecthibernate2.repository;

import org.example.safargulov.projecthibernate2.entity.Film;

public class FilmRepository extends AbstractRepository<Film, Integer> {
    public FilmRepository() {
        super(Film.class);
    }
}
