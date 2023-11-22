package com.ra.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {
    private static final String DRIVE_JDBC = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/ss13_spring_controller";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection openConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVE_JDBC);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;

    }
    public static void closeConnection( Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

