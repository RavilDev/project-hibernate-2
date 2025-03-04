package org.example.safargulov.projecthibernate2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {
    private String address;
    private String address2;
    private String district;
    private String postalCode;
    private String phone;
    private CityDto city;
}
