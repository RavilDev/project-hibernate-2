package org.example.safargulov.projecthibernate2.repository;

import org.example.safargulov.projecthibernate2.entity.Inventory;

public class InventoryRepository extends AbstractRepository<Inventory, Integer> {
    public InventoryRepository() {
        super(Inventory.class);
    }
}
