package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class DatabaseQueryUtil {
    public static String readQueryFromFile(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            throw new DatabaseQueryException("Can't read query from file", e);
        }
    }

    public static void executeQueryAndPrintResultInTable(String query) {
        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            StringBuilder columnHeaderBuilder = new StringBuilder();
            for (int i = 1; i <= columnCount; i++) {
                columnHeaderBuilder.append(String.format(DB_TABLE_ROW_FORMAT, metaData.getColumnLabel(i)));
            }
            logger.info(columnHeaderBuilder.toString());

            while (resultSet.next()) {
                StringBuilder rowBuilder = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    rowBuilder.append(String.format(DB_TABLE_ROW_FORMAT, resultSet.getString(i)));
                }
                logger.info(rowBuilder.toString());
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException(String.format("Error executing query and printing result in table: %s", query), e);
        }
    }
}
