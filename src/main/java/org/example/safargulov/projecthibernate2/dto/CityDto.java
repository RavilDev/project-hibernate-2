package org.example.safargulov.projecthibernate2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityDto {
    private String city;
    private CountryDto country;
}
