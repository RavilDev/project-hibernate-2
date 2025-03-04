package org.example.safargulov.projecthibernate2.repository;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

public interface BaseRepository<T, ID extends Serializable> {

    Optional<T> find(ID id);

    Stream<T> findAll();

    void save(T t);

    void delete(T t);

    Optional<T> update(T t);
}
