package ru.clevertec.dao.impl;

import ru.clevertec.aop.annotation.DeletePlayer;
import ru.clevertec.aop.annotation.GetPlayer;
import ru.clevertec.aop.annotation.PostPlayer;
import ru.clevertec.aop.annotation.PutPlayer;
import ru.clevertec.dao.Dao;
import ru.clevertec.db.DatabaseConnection;
import ru.clevertec.entity.Player;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PlayerDao implements Dao<Player> {
    private final Connection connection = DatabaseConnection.getConnection();

    /**
     * Запрос на запись данных в таблицу БД
     */
    private static final String SQL_INSERT_PLAYER = "INSERT INTO players (id, name, surname, date_birth, number) VALUES (?, ?, ?, ?, ?)";

    /**
     * Запрос на вывод данных по id
     */
    private static final String SQL_SELECT_PLAYER_BY_ID = "SELECT * FROM players WHERE id = ?";

    /**
     * Запрос на вывод всех данных из таблицы
     */
    private static final String SQL_SELECT_ALL_PLAYERS = "SELECT * FROM players";


    /**
     * Запрос на удаление данных из таблицы БД по id
     */
    private static final String SQL_DELETE_PLAYER = "DELETE FROM players WHERE id = ?";

    /**
     * Запрос на обновление данных из таблицы БД
     */
    private static final String SQL_UPDATE_PLAYER = "UPDATE players SET name = ?, surname = ?, date_birth = ?, number = ? WHERE id = ?";

    @Override
    @PostPlayer
    public Player save(Player player) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_PLAYER)) {
            statement.setObject(1, player.getId());
            statement.setString(2, player.getName());
            statement.setString(3, player.getSurname());
            statement.setDate(4, Date.valueOf(player.getDateBirth()));
            statement.setInt(5, player.getNumber());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }

    @Override
    @GetPlayer
    public Optional<Player> findById(UUID id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_PLAYER_BY_ID)) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Player player = mapResultSetToPlayer(resultSet);
                return Optional.of(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Player> findAll() {
        List<Player> players = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_PLAYERS)) {

            while (resultSet.next()) {
                Player player = mapResultSetToPlayer(resultSet);
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    @PutPlayer
    public void update(UUID id, Player player) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PLAYER)) {
            statement.setString(1, player.getName());
            statement.setString(2, player.getSurname());
            statement.setDate(3, Date.valueOf(player.getDateBirth()));
            statement.setInt(4, player.getNumber());
            statement.setObject(5, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    @DeletePlayer
    public void delete(UUID id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_PLAYER)) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Player mapResultSetToPlayer(ResultSet resultSet) throws SQLException {
        UUID id = (UUID) resultSet.getObject("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        LocalDate dateOfBirth = resultSet.getDate("date_birth").toLocalDate();
        int number = resultSet.getInt("number");

        return new Player(id, name, surname, dateOfBirth, number);
    }
}
