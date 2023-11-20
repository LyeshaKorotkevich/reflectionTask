package ru.clevertec.exception;

import java.util.UUID;

public class PlayerNotFoundException extends RuntimeException {

    /**
     * Сообщение должно быть именно такого формата
     *
     * @param id - идентификатор игрока
     */
    public PlayerNotFoundException(UUID id) {
        super(String.format("Player with id: %s not found", id));
    }
}
