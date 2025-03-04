package org.example.safargulov.projecthibernate2.service;

import lombok.Getter;
import org.example.safargulov.projecthibernate2.dto.StoreDto;
import org.example.safargulov.projecthibernate2.entity.Store;
import org.example.safargulov.projecthibernate2.repository.BaseRepository;


public class StoreService {
    @Getter
    private final BaseRepository<Store, Integer> repository;
    private final StaffService staffService;
    private final AddressService addressService;

    public StoreService(BaseRepository<Store, Integer> repository, StaffService staffService, AddressService addressService) {
        this.repository = repository;
        this.staffService = staffService;
        this.addressService = addressService;
    }

    public StoreDto toDto(Store store) {
        return StoreDto.builder()
                .manager(staffService.toDto(store.getManager()))
                .address(addressService.toDto(store.getAddress()))
                .build();
    }

    public Store toEntity(StoreDto storeDto) {
        return Store.builder()
                .manager(staffService.toEntity(storeDto.getManager()))
                .address(addressService.toEntity(storeDto.getAddress()))
                .build();
    }

}
