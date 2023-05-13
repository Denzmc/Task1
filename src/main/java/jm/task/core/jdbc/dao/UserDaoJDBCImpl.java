package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection conn = Util.connect();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = conn.createStatement()){
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                    "    id SERIAL PRIMARY KEY," +
                    "    firstname CHARACTER VARYING(30)," +
                    "    lastname CHARACTER VARYING(30)," +
                    "    age INTEGER" +
                    ");");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = conn.createStatement()){
            statement.executeUpdate("DROP TABLE users;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = conn.createStatement()){
            statement.executeUpdate("INSERT INTO users (firstname,lastname,age) VALUES ('"+
                    name+"','"+lastName+"',"+age+");");
            System.out.println("User с именем – "+name+" добавлен в базу данных ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = conn.createStatement()){
            statement.executeUpdate("DELETE FROM users WHERE id = "+id+";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()){
//                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                users.add(new User(name, lastName, age));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = conn.createStatement()){
            statement.executeUpdate("DELETE FROM users;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //
    }
}
