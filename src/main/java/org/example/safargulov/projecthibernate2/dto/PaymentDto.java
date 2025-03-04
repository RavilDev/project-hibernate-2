package org.example.safargulov.projecthibernate2.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private CustomerDto customer;
    private StaffDto staff;
    private RentalDto rental;
}
