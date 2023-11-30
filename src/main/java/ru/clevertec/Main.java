package ru.clevertec;

import ru.clevertec.dao.impl.PlayerDao;
import ru.clevertec.mapper.PlayerMapperImpl;
import ru.clevertec.service.impl.PlayerServiceImpl;
import ru.clevertec.view.PdfPrinter;

public class Main {
    public static void main(String[] args) {
        PdfPrinter printer = new PdfPrinter(new PlayerServiceImpl(new PlayerDao(), new PlayerMapperImpl()));
        printer.print();
//        PlayerServiceImpl playerService = new PlayerServiceImpl();
//        PlayerDto playerDto = new PlayerDto("Короткевич", "Алексей", LocalDate.of(2003, 11, 18), 10);
//        PlayerDto playerDto2 = new PlayerDto("Короткевич", "Александр", LocalDate.of(2006, 3, 9), 10);
//
//        UUID id = playerService.create(playerDto);
//        System.out.println(playerService.get(id));
//
//        playerService.update(id, playerDto2);
//
//        playerService.getAll()
//                .forEach(System.out::println);

        //playerService.delete(UUID.fromString("6640b372-a1d6-4e36-9fb1-cfbf7cee22b0"));
    }
}