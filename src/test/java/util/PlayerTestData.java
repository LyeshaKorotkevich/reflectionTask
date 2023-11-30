package util;

import lombok.Builder;
import ru.clevertec.dto.PlayerDto;
import ru.clevertec.entity.Player;

import java.time.LocalDate;
import java.util.UUID;

@Builder(setterPrefix = "with")
public class PlayerTestData {

    @Builder.Default
    private UUID id = UUID.fromString("e2f53c51-0f2d-4d66-b3fc-e59624aa8cf6");

    @Builder.Default
    private String name = "Lyesha";

    @Builder.Default
    private String surname = "Korotkevich";

    @Builder.Default
    private LocalDate dateBirth = LocalDate.of(2023, 11, 18);

    @Builder.Default
    private int number = 10;

    public Player buildPlayer() {
        return Player.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .dateBirth(dateBirth)
                .number(number)
                .build();
    }

    public PlayerDto buildPlayerDto() {
        return new PlayerDto(name, surname, dateBirth, number);
    }
}
