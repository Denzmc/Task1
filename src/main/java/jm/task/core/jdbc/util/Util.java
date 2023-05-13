package jm.task.core.jdbc.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:postgresql://localhost:5432/main";
    private static final String user = "postgres";
    private static final String password = "postgres";

    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            System.out.println("Connection failed...");
            throw new RuntimeException(e);
        }

        return conn;
    }


    // jdbc:postgresql://localhost:5432/main
}
