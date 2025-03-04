package org.example.safargulov.projecthibernate2.repository;

import org.example.safargulov.projecthibernate2.entity.Category;

public class CategoryRepository extends AbstractRepository<Category, Integer> {
    public CategoryRepository() {
        super(Category.class);
    }
}
