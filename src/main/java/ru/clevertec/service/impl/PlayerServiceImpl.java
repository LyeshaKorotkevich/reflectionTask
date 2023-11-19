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
    private final Dao<Player> playerDao = new PlayerDao();
    private final PlayerMapper mapper = new PlayerMapperImpl();

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
            if(PlayerValidator.validate(playerDto)) {
                return playerDao.save(mapper.toPlayer(playerDto)).getId();
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(UUID uuid, PlayerDto playerDto) {
        try {
            if(PlayerValidator.validate(playerDto)) {
                Player player = playerDao.findById(uuid).orElseThrow(() -> new PlayerNotFoundException(uuid));
                playerDao.update(mapper.merge(player, playerDto));
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
