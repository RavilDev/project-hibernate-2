package org.example.safargulov.projecthibernate2.service;

import lombok.Getter;
import org.example.safargulov.projecthibernate2.dto.LanguageDto;
import org.example.safargulov.projecthibernate2.entity.Language;
import org.example.safargulov.projecthibernate2.repository.BaseRepository;

@Getter
public class LanguageService {
    private final BaseRepository<Language, Integer> repository;

    public LanguageService(BaseRepository<Language, Integer> repository) {
        this.repository = repository;
    }

    public LanguageDto toDto(Language language) {
        return LanguageDto.builder()
                .name(language.getName())
                .build();
    }

    public Language toEntity(LanguageDto languageDto) {
        return Language.builder()
                .name(languageDto.getName())
                .build();
    }
}
