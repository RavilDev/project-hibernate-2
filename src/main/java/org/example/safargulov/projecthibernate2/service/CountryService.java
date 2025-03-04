package org.example.safargulov.projecthibernate2.service;

import org.example.safargulov.projecthibernate2.dto.CountryDto;
import org.example.safargulov.projecthibernate2.entity.Country;
import org.example.safargulov.projecthibernate2.repository.BaseRepository;

public class CountryService {
    BaseRepository<Country, Integer> repository;

    public CountryDto toDto(Country country) {
        CountryDto countryDto = new CountryDto();
        countryDto.setCountry(country.getCountry());
        return countryDto;
    }

    public Country toEntity(CountryDto countryDto) {
        Country country = new Country();
        country.setCountry(countryDto.getCountry());
        return country;
    }

}
