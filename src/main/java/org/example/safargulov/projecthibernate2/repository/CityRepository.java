package org.example.safargulov.projecthibernate2.repository;

import org.example.safargulov.projecthibernate2.entity.City;

public class CityRepository extends AbstractRepository<City, Integer> {
    public CityRepository() {
        super(City.class);
    }
}
