package org.example.safargulov.projecthibernate2.service;

import org.example.safargulov.projecthibernate2.dto.CityDto;
import org.example.safargulov.projecthibernate2.entity.City;
import org.example.safargulov.projecthibernate2.repository.BaseRepository;

public class CityService {
    private final BaseRepository<City, Integer> repository;
    private final CountryService countryService;

    public CityService(BaseRepository<City, Integer> repository, CountryService countryService) {
        this.repository = repository;
        this.countryService = countryService;
    }

    public CityDto toDto(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setCity(city.getCity());
        cityDto.setCountry(countryService.toDto(city.getCountry()));
        return cityDto;
    }

    public City toEntity(CityDto cityDto) {
        City city = new City();
        city.setCity(cityDto.getCity());
        city.setCountry(countryService.toEntity(cityDto.getCountry()));
        return city;
    }
}
