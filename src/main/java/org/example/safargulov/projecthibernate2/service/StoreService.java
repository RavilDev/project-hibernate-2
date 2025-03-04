package org.example.safargulov.projecthibernate2.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.safargulov.projecthibernate2.dto.StoreDto;
import org.example.safargulov.projecthibernate2.entity.Store;
import org.example.safargulov.projecthibernate2.repository.BaseRepository;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class StoreService {

    private final BaseRepository<Store, Byte> repository;
    private final StaffService staffService;
    private final AddressService addressService;

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

    public Optional<Store> find(Byte id) {
        return repository.find(id);
    }

}
