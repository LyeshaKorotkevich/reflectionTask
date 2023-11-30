package ru.clevertec.service.impl;

import ru.clevertec.dao.Dao;
import ru.clevertec.dao.impl.PlayerDao;
import ru.clevertec.dto.PlayerDto;
import ru.clevertec.entity.Player;
import ru.clevertec.exception.PlayerNotFoundException;
import ru.clevertec.mapper.PlayerMapper;
import ru.clevertec.mapper.PlayerMapperImpl;
import ru.clevertec.service.PlayerService;
import ru.clevertec.validator.PlayerValidator;

import java.util.List;
import java.util.UUID;

public class PlayerServiceImpl implements PlayerService {
    private final Dao<Player> playerDao;
    private final PlayerMapper mapper;

    public PlayerServiceImpl(Dao<Player> playerDao, PlayerMapper mapper) {
        this.playerDao = playerDao;
        this.mapper = mapper;
    }

    @Override
    public PlayerDto get(UUID id) {
        Player player = playerDao.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));
        return mapper.toPlayerDto(player);
    }

    @Override
    public List<PlayerDto> getAll() {
        return playerDao.findAll().stream()
                .map(mapper::toPlayerDto)
                .toList();
    }

    @Override
    public UUID create(PlayerDto playerDto) {
        try {
            if (PlayerValidator.validate(playerDto)) {
                Player player = mapper.toPlayer(playerDto);
                player.setId(UUID.randomUUID());
                return playerDao.save(player).getId();
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(UUID id, PlayerDto playerDto) {
        try {
            if (PlayerValidator.validate(playerDto)) {
                Player player = playerDao.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));
                playerDao.update(id, mapper.merge(player, playerDto));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(UUID id) {
        playerDao.delete(id);
    }
}
