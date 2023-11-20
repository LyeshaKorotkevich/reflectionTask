package ru.clevertec.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Интерфейс для работы с объектами в базе данных.
 */
public interface Dao<T> {

    /**
     * Создает новый объект.
     *
     * @param obj Данные нового объекта.
     * @return Объект T.
     */
    T save(T obj);

    /**
     * Получает объект по его id.
     *
     * @param id id объекта.
     * @return Объект Optional<T>, представляющий найденный объект.
     */
    Optional<T> findById(UUID id);

    /**
     * Получает список всех объектов.
     *
     * @return Список всех объектов.
     */
    List<T> findAll();

    /**
     * Обновляет информацию об объекте.
     *
     * @param obj Объект для обновления.
     */
    void update(UUID id, T obj);

    /**
     * Удаляет объект по его id.
     *
     * @param id id объекта для удаления.
     */
    void delete(UUID id);
}
