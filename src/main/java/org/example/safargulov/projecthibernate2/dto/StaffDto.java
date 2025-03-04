package org.example.safargulov.projecthibernate2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private AddressDto address;
    private StoreDto store;
}
