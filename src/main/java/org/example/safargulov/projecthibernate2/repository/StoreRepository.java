package org.example.safargulov.projecthibernate2.repository;

import org.example.safargulov.projecthibernate2.entity.Store;

public class StoreRepository extends AbstractRepository<Store, Integer> {

    public StoreRepository() {
        super(Store.class);
    }

}
