package org.example.safargulov.projecthibernate2.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.safargulov.projecthibernate2.dto.StaffDto;
import org.example.safargulov.projecthibernate2.entity.Staff;
import org.example.safargulov.projecthibernate2.repository.BaseRepository;

@Getter
@AllArgsConstructor
public class StaffService {
    private final BaseRepository<Staff, Integer> repository;
    private final StoreService storeService;
    private final AddressService addressService;

    public StaffDto toDto(Staff staff) {
        return StaffDto.builder()
                .email(staff.getEmail())
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .password(staff.getPassword())
                .username(staff.getUsername())
                .address(addressService.toDto(staff.getAddress()))
                .store(storeService.toDto(staff.getStore()))
                .build();
    }

    public Staff toEntity(StaffDto staffDto) {
        return Staff.builder()
                .email(staffDto.getEmail())
                .firstName(staffDto.getFirstName())
                .lastName(staffDto.getLastName())
                .password(staffDto.getPassword())
                .username(staffDto.getUsername())
                .address(addressService.toEntity(staffDto.getAddress()))
                .store(storeService.toEntity(staffDto.getStore()))
                .build();
    }
}
