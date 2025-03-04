package org.example.safargulov.projecthibernate2.service;

import org.example.safargulov.projecthibernate2.config.SessionCreator;
import org.example.safargulov.projecthibernate2.dto.*;
import org.example.safargulov.projecthibernate2.entity.Customer;
import org.example.safargulov.projecthibernate2.entity.Inventory;
import org.example.safargulov.projecthibernate2.entity.Payment;
import org.example.safargulov.projecthibernate2.entity.Rental;
import org.example.safargulov.projecthibernate2.repository.BaseRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public class CustomerService {
    private final BaseRepository<Customer, Integer> customerRepository;
    private final AddressService addressService;
    private final StoreService storeService;
    private final RentalService rentalService;
    private final StaffService staffService;
    private final InventoryService inventoryService;


    public CustomerService(BaseRepository<Customer, Integer> repository, AddressService addressService, StoreService storeService, RentalService rentalService, StaffService staffService, InventoryService inventoryService) {
        this.customerRepository = repository;
        this.addressService = addressService;
        this.storeService = storeService;
        this.rentalService = rentalService;
        this.staffService = staffService;
        this.inventoryService = inventoryService;
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

    public RentalDto rentFilmAndPayInStore(CustomerDto customerDto,
                                           StaffDto staffDto,
                                           Integer inventoryId,
                                           Integer storeId
    ) throws Exception {
        SessionCreator creator = new SessionCreator();
        Transaction transaction = null;
        try (Session session = creator.getSession(); creator) {
            transaction = session.beginTransaction();
            String hql = "SELECT r FROM Rental r WHERE r.inventory.id = :id AND r.returnDate IS NULL " +
                    "AND r.inventory.store.id = :storeId";
            Query<Rental> query = session.createQuery(hql, Rental.class);
            query.setParameter("id", inventoryId);
            query.setParameter("storeId", storeId);
            if (query.getResultStream().toList().isEmpty()) {
                Optional<Inventory> inventoryOpt = inventoryService.find(inventoryId);
                if (inventoryOpt.isEmpty()) {
                    throw new IllegalArgumentException("Inventory not found");
                }
                Inventory inventory = inventoryOpt.get();
                Rental rental = Rental.builder()
                        .staff(staffService.toEntity(staffDto))
                        .customer(this.toEntity(customerDto))
                        .rentalDate(LocalDateTime.now())
                        .inventory(inventory)
                        .build();
                Payment payment = Payment.builder()
                        .amount(100)
                        .customer(this.toEntity(customerDto))
                        .paymentDate(LocalDateTime.now())
                        .staff(staffService.toEntity(staffDto))
                        .rental(rental)
                        .build();
                session.persist(rental);
                session.persist(payment);
                transaction.commit();
                return rentalService.toDto(rental);
            } else {
                throw new IllegalStateException("Inventory is not available for rental");
            }
        } catch (Exception e) {
            if (transaction == null) throw new AssertionError();
            transaction.rollback();
            throw e;
        }
    }

}
