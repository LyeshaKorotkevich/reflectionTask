package ru.clevertec.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Класс, описывающий игрока
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Player {

    /**
     * Поле идентификатор
     */
    private UUID id;

    /**
     * Поле имени игрока
     */
    private String name;

    /**
     * Поле фамилии игрока
     */
    private String surname;

    /**
     * Поле даты рождения игрока
     */
    private LocalDate dateBirth;

    /**
     * Поле номера на футболке игрока
     */
    private int number;

}
