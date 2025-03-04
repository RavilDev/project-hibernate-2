package org.example.safargulov.projecthibernate2.service;

import org.example.safargulov.projecthibernate2.dto.AddressDto;
import org.example.safargulov.projecthibernate2.dto.CustomerDto;
import org.example.safargulov.projecthibernate2.dto.RentalDto;
import org.example.safargulov.projecthibernate2.dto.StoreDto;
import org.example.safargulov.projecthibernate2.entity.Customer;
import org.example.safargulov.projecthibernate2.entity.Rental;
import org.example.safargulov.projecthibernate2.repository.BaseRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class CustomerService {
    private final BaseRepository<Customer, Integer> customerRepository;
    private final AddressService addressService;
    private final StoreService storeService;
    private final RentalService rentalService;


    public CustomerService(BaseRepository<Customer, Integer> repository, AddressService addressService, StoreService storeService, RentalService rentalService) {
        this.customerRepository = repository;
        this.addressService = addressService;
        this.storeService = storeService;
        this.rentalService = rentalService;
    }

    public CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .active(customer.isActive())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(addressService.toDto(customer.getAddress()))
                .store(storeService.toDto(customer.getStore()))
                .build();
    }

    public Customer toEntity(CustomerDto customerDto) {
        return Customer.builder()
                .active(customerDto.isActive())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .address(addressService.toEntity(customerDto.getAddress()))
                .store(storeService.toEntity(customerDto.getStore()))
                .build();
    }

    public CustomerDto createCustomer(CustomerDto customerDto, StoreDto storeDto, AddressDto addressDto) {
        Customer customer = toEntity(customerDto);
        customer.setStore(storeService.toEntity(storeDto));
        customer.setAddress(addressService.toEntity(addressDto));
        customerRepository.save(customer);
        return toDto(customer);
    }

    public RentalDto returnRentalFilm(Integer customerId, Integer rentalId) {
        Optional<Rental> rental = rentalService.find(rentalId);
        Optional<Customer> customer = customerRepository.find(customerId);
        if (rental.isEmpty() || customer.isEmpty()) {
            throw new IllegalArgumentException("Rental or Customer not found");
        }
        Rental rentalToReturn = rental.get();
        Customer customerToReturn = customer.get();
        if (rentalToReturn.getCustomer().equals(customerToReturn)) {
            if (rentalToReturn.getReturnDate() == null) {
                rentalToReturn.setReturnDate(LocalDateTime.now());
                rentalService.save(rentalToReturn);
                return rentalService.toDto(rentalToReturn);
            }
            throw new IllegalStateException("Rental already returned");
        }
        throw new IllegalArgumentException("Rental does not belong to this customer");
    }

}
