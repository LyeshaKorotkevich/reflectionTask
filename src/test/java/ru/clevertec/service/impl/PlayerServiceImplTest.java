package ru.clevertec.service.impl;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.dao.Dao;
import ru.clevertec.dto.PlayerDto;
import ru.clevertec.entity.Player;
import ru.clevertec.mapper.PlayerMapper;
import ru.clevertec.validator.PlayerValidator;
import util.PlayerTestData;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @Mock
    private Dao<Player> playerDao;

    @Mock
    private PlayerMapper playerMapper;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Captor
    private ArgumentCaptor<Player> playerCaptor;

    @Test
    void getShouldReturnPlayerDto() {
        // given
        PlayerDto expected = PlayerTestData.builder().build().buildPlayerDto();
        Player playerToFind = PlayerTestData.builder().build().buildPlayer();

        when(playerDao.findById(playerToFind.getId()))
                .thenReturn(Optional.of(playerToFind));

        when(playerMapper.toPlayerDto(playerToFind))
                .thenReturn(expected);

        // when
        PlayerDto actual = playerService.get(playerToFind.getId());

        // then
        assertEquals(expected, actual);
    }

    @Test
    void createShouldReturnUuid() throws IllegalAccessException {
        // given
        PlayerDto playerDto = PlayerTestData.builder().build().buildPlayerDto();
        Player playerToSave = PlayerTestData.builder().build().buildPlayer();

        when(playerMapper.toPlayer(playerDto))
                .thenReturn(playerToSave);

        when(playerDao.save(playerToSave))
                .thenReturn(playerToSave);

        // when
        UUID actual = playerService.create(playerDto);

        // then
        assertNotNull(actual);
    }

    @Test
    void updatePlayerWithNewDetails() throws IllegalAccessException {
        // given
        PlayerDto playerDtoForUpdate = PlayerTestData.builder().withName("GG").build().buildPlayerDto();
        Player playerToUpdate = PlayerTestData.builder().build().buildPlayer();
        Player expected =  PlayerTestData.builder().withName("GG").build().buildPlayer();

        when(playerDao.findById(playerToUpdate.getId()))
                .thenReturn(Optional.of(playerToUpdate));

        doReturn(expected)
                .when(playerMapper)
                .merge(playerToUpdate, playerDtoForUpdate);

        doNothing()
                .when(playerDao)
                .update(playerToUpdate.getId(), expected);

        // when
        playerService.update(playerToUpdate.getId(), playerDtoForUpdate);

        // then
        verify(playerDao)
                .update(eq(playerToUpdate.getId()), playerCaptor.capture());
        Player actual = playerCaptor.getValue();
        assertThat(actual)
                .hasFieldOrPropertyWithValue(Player.Fields.name, expected.getName());
    }

    @Test
    void deleteShouldInvokeOneTime() {
        // given
        UUID uuid = UUID.randomUUID();

        // when
        playerService.delete(uuid);

        // then
        verify(playerDao).delete(uuid);
    }
}