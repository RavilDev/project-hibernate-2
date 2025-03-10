package org.example.safargulov.projecthibernate2.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.safargulov.projecthibernate2.dto.InventoryDto;
import org.example.safargulov.projecthibernate2.entity.Inventory;
import org.example.safargulov.projecthibernate2.repository.BaseRepository;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class InventoryService {
    private final BaseRepository<Inventory, Integer> repository;
    private final StoreService storeService;
    private final FilmService filmService;

    public InventoryDto toDto(Inventory inventory) {
        return InventoryDto.builder()
                .film(filmService.toDto(inventory.getFilm()))
                .store(storeService.toDto(inventory.getStore()))
                .build();
    }

    public Inventory toEntity(InventoryDto inventoryDto) {
        return Inventory.builder()
                .film(filmService.toEntity(inventoryDto.getFilm()))
                .store(storeService.toEntity(inventoryDto.getStore()))
                .build();
    }


    public Optional<Inventory> find(Integer id) {
        return repository.find(id);
    }

}
