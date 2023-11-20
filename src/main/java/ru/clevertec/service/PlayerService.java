package ru.clevertec.service;

import ru.clevertec.dto.PlayerDto;

import java.util.List;
import java.util.UUID;

public interface PlayerService {
    /**
     * Ищет игрока по идентификатору
     *
     * @param id идентификатор игрока
     * @return найденный игрок
     * @throws ru.clevertec.exception.PlayerNotFoundException если не найден
     */
    PlayerDto get(UUID id);

    /**
     * Возвращает всх существующих игроков
     *
     * @return лист с информацией о игроках
     */
    List<PlayerDto> getAll();

    /**
     * Создаёт новог игрока из DTO
     *
     * @param playerDto DTO с информацией о создании
     * @return идентификатор созданного игрока
     */
    UUID create(PlayerDto playerDto);

    /**
     * Обновляет уже существующего игрока из информации полученной в DTO
     *
     * @param uuid      идентификатор игрока для обновления
     * @param playerDto DTO с информацией об обновлении
     */
    void update(UUID uuid, PlayerDto playerDto);

    /**
     * Удаляет существующого игрока
     *
     * @param id идентификатор игрока для удаления
     */
    void delete(UUID id);
}
