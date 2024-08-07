package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static Connection connection;
    private static final String connectionURL = "jdbc:mysql://localhost:3306/dbtesting";
    private static final String user = "root";
    private static final String password = "aShabtamaev2001";
    public static Connection getConnection()  {;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionURL, user, password);
            System.out.println("Connected to database" + connection);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    // реализуйте настройку соеденения с БД
}
}
