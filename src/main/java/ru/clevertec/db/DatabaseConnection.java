package ru.clevertec.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Утилитарный класс для установления и закрытия соединения с базой данных PostgreSQL.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5438/postgres";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "root";

    /**
     * Получает соединение с базой данных.
     *
     * @return Объект типа Connection для работы с базой данных.
     * @throws RuntimeException если возникает ошибка при подключении к базе данных.
     */
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при подключении к базе данных");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Закрывает соединение с базой данных.
     *
     * @param connection Соединение для закрытия.
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
