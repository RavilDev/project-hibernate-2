package org.example.safargulov.projecthibernate2.repository;

import org.example.safargulov.projecthibernate2.entity.Staff;

public class StaffRepository extends AbstractRepository<Staff, Integer> {
    public StaffRepository() {
        super(Staff.class);
    }
}
