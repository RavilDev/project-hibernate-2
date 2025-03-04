package org.example.safargulov.projecthibernate2.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.safargulov.projecthibernate2.dto.RentalDto;
import org.example.safargulov.projecthibernate2.entity.Rental;
import org.example.safargulov.projecthibernate2.repository.BaseRepository;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class RentalService {
    private final BaseRepository<Rental, Integer> rentalRepo;
    private final StaffService staffService;
    private final CustomerService customerService;
    private final InventoryService inventoryService;

    public RentalDto toDto(Rental rental) {
        return RentalDto.builder()
                .staff(staffService.toDto(rental.getStaff()))
                .customer(customerService.toDto(rental.getCustomer()))
                .inventory(inventoryService.toDto(rental.getInventory()))
                .rentalDate(rental.getRentalDate())
                .returnDate(rental.getReturnDate())
                .build();
    }

    public Rental toEntity(RentalDto rentalDto) {
        return Rental.builder()
                .staff(staffService.toEntity(rentalDto.getStaff()))
                .customer(customerService.toEntity(rentalDto.getCustomer()))
                .inventory(inventoryService.toEntity(rentalDto.getInventory()))
                .rentalDate(rentalDto.getRentalDate())
                .returnDate(rentalDto.getReturnDate())
                .build();
    }

    public void save(Rental rental) {
        rentalRepo.save(rental);
    }

    public Optional<Rental> find(Integer id) {
        return rentalRepo.find(id);
    }
}
