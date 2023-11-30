package ru.clevertec.mapper.impl;

import org.junit.jupiter.api.Test;
import ru.clevertec.dto.PlayerDto;
import ru.clevertec.entity.Player;
import ru.clevertec.mapper.PlayerMapper;
import ru.clevertec.mapper.PlayerMapperImpl;
import util.PlayerTestData;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerMapperImplTest {

    private final PlayerMapper playerMapper = new PlayerMapperImpl();

    @Test
    void toPlayerShouldReturnPlayer() {
        // given
        PlayerDto playerDto = PlayerTestData.builder().build().buildPlayerDto();
        Player expected = PlayerTestData.builder().withId(null).build().buildPlayer();

        // when
        Player actual = playerMapper.toPlayer(playerDto);

        // then
        assertThat(actual)
                .hasFieldOrPropertyWithValue(Player.Fields.id, expected.getId())
                .hasFieldOrPropertyWithValue(Player.Fields.name, expected.getName())
                .hasFieldOrPropertyWithValue(Player.Fields.surname, expected.getSurname())
                .hasFieldOrPropertyWithValue(Player.Fields.dateBirth, expected.getDateBirth())
                .hasFieldOrPropertyWithValue(Player.Fields.number, expected.getNumber());
    }

    @Test
    void toPlayerDtoShouldReturnPlayerDto() {
        // given
        Player player = PlayerTestData.builder().build().buildPlayer();
        PlayerDto expected = PlayerTestData.builder().build().buildPlayerDto();

        // when
        PlayerDto actual = playerMapper.toPlayerDto(player);

        // then
        assertEquals(expected, actual);
    }

    @Test
    void mergeShouldReturnPlayer() {
        // given
        Player player = PlayerTestData.builder().build().buildPlayer();
        PlayerDto playerDto = PlayerTestData.builder().withName("newName").build().buildPlayerDto();
        Player expected = PlayerTestData.builder().withName("newName").build().buildPlayer();

        // when
        Player actual = playerMapper.merge(player, playerDto);

        // then
        assertEquals(expected, actual);
    }
}
