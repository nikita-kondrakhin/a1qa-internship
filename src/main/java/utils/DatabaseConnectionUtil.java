package utils;

import constants.ConfigData;
import constants.TestData;
import exceptions.DatabaseConnectionException;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@UtilityClass
public class DatabaseConnectionUtil {
    private static Connection dbConnection = null;

    public static Connection getConnection() {
        try {
            if (dbConnection == null || dbConnection.isClosed()) {
                dbConnection = DriverManager.getConnection(ConfigData.DB_CONNECTION_URL, TestData.USER, TestData.PASSWORD);
            }
            return dbConnection;
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to connect to database", e);
        }
    }

    public static void closeConnection() {
        try {
            if (dbConnection != null && !dbConnection.isClosed()) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to close database connection", e);
        }
    }
}
