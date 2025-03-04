package org.example.safargulov.projecthibernate2.repository;

import org.example.safargulov.projecthibernate2.entity.Language;

public class LanguageRepository extends AbstractRepository<Language, Integer> {
    public LanguageRepository() {
        super(Language.class);
    }
}
