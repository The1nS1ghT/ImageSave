package com.save.save.config;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
public class ConnectionDB {
    public static Connection getMyConnection() throws SQLException {
        String hostName = "localhost";
        String dbName = "ImageSave";
        String userName = "postgres";
        String password = "Insight";

        return getMyPostgresConnection(hostName, dbName, userName, password);
    }
    public static Connection getMyPostgresConnection(String hostName, String dbName,
                                                     String userName, String password) throws SQLException {

        String connectionURL = "jdbc:postgresql://" + hostName + ":5432/" + dbName;

        Connection connection = DriverManager.getConnection(connectionURL, userName,
                password);
        return connection;
    }
}

