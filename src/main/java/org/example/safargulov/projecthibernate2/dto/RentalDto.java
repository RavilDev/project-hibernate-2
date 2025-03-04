package org.example.safargulov.projecthibernate2.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalDto {
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private InventoryDto inventory;
    private CustomerDto customer;
    private StaffDto staff;
}
