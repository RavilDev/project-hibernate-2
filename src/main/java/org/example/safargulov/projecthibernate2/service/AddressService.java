package org.example.safargulov.projecthibernate2.service;

import lombok.Getter;
import org.example.safargulov.projecthibernate2.dto.AddressDto;
import org.example.safargulov.projecthibernate2.entity.Address;
import org.example.safargulov.projecthibernate2.repository.BaseRepository;

public class AddressService {
    @Getter
    private final BaseRepository<Address, Integer> repository;
    private final CityService cityService;

    public AddressService(BaseRepository<Address, Integer> repository, CityService cityService) {
        this.repository = repository;
        this.cityService = cityService;
    }

    public AddressDto toDto(Address address) {
        return AddressDto.builder()
                .address(address.getAddress())
                .address2(address.getAddress2())
                .phone(address.getPhone())
                .district(address.getDistrict())
                .postalCode(address.getPostalCode())
                .city(cityService.toDto(address.getCity()))
                .build();
    }

    public Address toEntity(AddressDto addressDto) {
        return Address.builder()
                .address(addressDto.getAddress())
                .address2(addressDto.getAddress2())
                .phone(addressDto.getPhone())
                .district(addressDto.getDistrict())
                .postalCode(addressDto.getPostalCode())
                .city(cityService.toEntity(addressDto.getCity()))
                .build();
    }

}
