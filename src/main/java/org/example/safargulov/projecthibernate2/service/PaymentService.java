package org.example.safargulov.projecthibernate2.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.safargulov.projecthibernate2.dto.PaymentDto;
import org.example.safargulov.projecthibernate2.entity.Payment;
import org.example.safargulov.projecthibernate2.repository.BaseRepository;

@Getter
@AllArgsConstructor
public class PaymentService {
    private final BaseRepository<Payment, Integer> repository;
    private final StaffService staffService;
    private final CustomerService customerService;
    private final RentalService rentalService;

    public PaymentDto toDto(Payment payment) {
        return PaymentDto.builder()
                .staff(staffService.toDto(payment.getStaff()))
                .customer(customerService.toDto(payment.getCustomer()))
                .rental(rentalService.toDto(payment.getRental()))
                .amount(payment.getAmount())
                .build();
    }

    public Payment toEntity(PaymentDto dto) {
        return Payment.builder()
                .staff(staffService.toEntity(dto.getStaff()))
                .customer(customerService.toEntity(dto.getCustomer()))
                .rental(rentalService.toEntity(dto.getRental()))
                .amount(dto.getAmount())
                .build();
    }

    public void save(Payment payment) {
        repository.save(payment);
    }
}
