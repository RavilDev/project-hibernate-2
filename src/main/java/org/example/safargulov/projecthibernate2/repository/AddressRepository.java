package org.example.safargulov.projecthibernate2.repository;

import org.example.safargulov.projecthibernate2.entity.Address;

public class AddressRepository extends AbstractRepository<Address, Integer> {

    public AddressRepository() {
        super(Address.class);
    }

}
