package org.example.safargulov.projecthibernate2.repository;

import org.example.safargulov.projecthibernate2.entity.Customer;

public class CustomerRepository extends AbstractRepository<Customer, Integer> {

    public CustomerRepository() {
        super(Customer.class);
    }

}
