package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.clevertec.dto.PlayerDto;
import ru.clevertec.entity.Player;

import java.util.UUID;

@Mapper
public interface PlayerMapper {

    /**
     * Маппит DTO в игрока без UUID
     *
     * @param playerDto - DTO для маппинга
     * @return новый игрок
     */
    @Mapping(target = "id", ignore = true)
    Player toPlayer(PlayerDto playerDto);

    /**
     * Маппит игрока в DTO
     *
     * @param player - игрок для маппинга
     * @return новый игрокDto
     */
    PlayerDto toPlayerDto(Player player);

    /**
     * Сливает существующего игрока с информацией из DTO
     * не меняет идентификатор
     *
     * @param player    существующий игрок
     * @param playerDto информация для обновления
     * @return обновлённый игрок
     */
    @Mapping(target = "id", ignore = true)
    Player merge(@MappingTarget Player player, PlayerDto playerDto);
}
