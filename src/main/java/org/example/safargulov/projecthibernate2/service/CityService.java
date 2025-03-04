package org.example.safargulov.projecthibernate2.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.safargulov.projecthibernate2.dto.CityDto;
import org.example.safargulov.projecthibernate2.entity.City;
import org.example.safargulov.projecthibernate2.repository.BaseRepository;

@Getter
@AllArgsConstructor
public class CityService {
    private final BaseRepository<City, Integer> repository;
    private final CountryService countryService;

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
