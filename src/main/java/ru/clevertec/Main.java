package ru.clevertec;

import ru.clevertec.dto.PlayerDto;
import ru.clevertec.service.impl.PlayerServiceImpl;

import java.time.LocalDate;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        PlayerServiceImpl playerService = new PlayerServiceImpl();
        PlayerDto playerDto = new PlayerDto("Короткевич", "Алексей", LocalDate.of(2003, 11, 18), 10);
        PlayerDto playerDto2 = new PlayerDto("Короткевич", "Александр", LocalDate.of(2006, 3, 9), 7);

        UUID id = playerService.create(playerDto);
        System.out.println(playerService.get(id));

        playerService.update(id, playerDto2);

        playerService.getAll()
                .forEach(System.out::println);

        //playerService.delete(UUID.fromString("6640b372-a1d6-4e36-9fb1-cfbf7cee22b0"));
    }
}