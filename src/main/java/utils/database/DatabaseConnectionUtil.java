package utils.database;

import constants.ConfigData;
import constants.TestData;
import exceptions.DatabaseConnectionException;
import lombok.experimental.UtilityClass;
import org.sql2o.*;

@UtilityClass
public class DatabaseConnectionUtil {
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null) {
                connection = new Sql2o(ConfigData.DB_CONNECTION_URL, TestData.USER, TestData.PASSWORD).open();
            }
            return connection;
        } catch (Sql2oException e) {
            throw new DatabaseConnectionException("Failed to connect to database", e);
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Sql2oException e) {
            throw new DatabaseConnectionException("Failed to close database connection", e);
        }
    }
}
