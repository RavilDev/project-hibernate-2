package org.example.safargulov.projecthibernate2.repository;

import org.example.safargulov.projecthibernate2.entity.Rental;

public class RentalRepository extends AbstractRepository<Rental, Integer> {
    public RentalRepository() {
        super(Rental.class);
    }
}
