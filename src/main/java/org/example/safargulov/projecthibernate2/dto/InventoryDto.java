package org.example.safargulov.projecthibernate2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryDto {
    private StoreDto store;
    private FilmDto film;
}
