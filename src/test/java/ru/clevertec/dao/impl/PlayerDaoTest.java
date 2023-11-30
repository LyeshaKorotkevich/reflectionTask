//package ru.clevertec.dao.impl;
//
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import ru.clevertec.dao.Dao;
//import ru.clevertec.entity.Player;
//import util.PlayerTestData;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class PlayerDaoTest {
//    private final Dao<Player> playerDao = new PlayerDao();
//
//    @Mock
//    private Connection connection;
//
//    @Mock
//    private PreparedStatement preparedStatement;
//
//    @Mock
//    private ResultSet resultSet;
//
//    @Nested
//    class SavePlayer {
//
//        @Test
//        void savePlayerShouldReturnPlayer() throws SQLException {
//            // given
//            Player player = PlayerTestData.builder().build().buildPlayer();
//            when(connection.prepareStatement(any(String.class)))
//                    .thenReturn(preparedStatement);
//            when(preparedStatement.executeUpdate())
//                    .thenReturn(1);
//
//            // when
//            Player savedPlayer = playerDao.save(player);
//
//            // then
//            assertEquals(player, savedPlayer);
//            verify(preparedStatement).setObject(1, player.getId());
//            verify(preparedStatement).setString(2, player.getName());
//            verify(preparedStatement).setString(3, player.getSurname());
//            verify(preparedStatement).setDate(4, Date.valueOf(player.getDateBirth()));
//            verify(preparedStatement).setInt(5, player.getNumber());
//            verify(preparedStatement).executeUpdate();
//        }
//
//        @Test
//        void saveNullPlayerShouldThrowException() {
//            // given
//            Player player = null;
//
//            // when, then
//            assertThrows(NullPointerException.class,
//                    () -> playerDao.save(player));
//        }
//
//    }
//}