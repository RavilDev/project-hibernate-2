package org.example.safargulov.projecthibernate2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private String firstName;
    private String lastName;
    private String email;
    private boolean active;
    private AddressDto address;
    private StoreDto store;

}
