package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection conn = Util.getConnection();
    private final String CREATE_TABLE_SQL = "create TABLE IF NOT EXISTS Users(id BIGINT NOT NULL AUTO_INCREMENT, name Varchar(255) NOT NULL, lastName VARCHAR(255) NOT NULL, age TINYINT NOT NULL, PRIMARY KEY (id))";
    private final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS Users";
    private final String SAVE_USER_SQL = "INSERT INTO Users( name, lastName, age) VALUES (?, ?, ?)";
    private final String REMOVE_USER_SQL = "DELETE FROM Users WHERE id = ?";
    private final String GET_USERS_SQL = "SELECT * FROM Users";
    private final String CLEAN_TABLE_SQL = "TRUNCATE TABLE Users";

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement stmt = conn.createStatement()) {
            int result = stmt.executeUpdate(CREATE_TABLE_SQL);
            System.out.println("Таблица создана или уже существует");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(DROP_TABLE_SQL);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement stmt = conn.prepareStatement(SAVE_USER_SQL)) {
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement stmt = conn.prepareStatement(REMOVE_USER_SQL);){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try (Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(GET_USERS_SQL);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement stmt = conn.createStatement()){
            stmt.executeUpdate(CLEAN_TABLE_SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
