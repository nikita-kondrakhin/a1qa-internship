package utils.database;

import exceptions.DatabaseQueryException;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;
import org.sql2o.data.Table;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DatabaseQueryUtil {
    public static String readQueryFromFile(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            throw new DatabaseQueryException("Can't read query from file", e);
        }
    }

    public static List<String> getDatabaseColumnValues(String query, String columnName) {
        try (Connection connection = DatabaseConnectionUtil.getConnection()) {
            Table table = connection.createQuery(query).executeAndFetchTable();
            return table.rows().stream()
                    .map(row -> row.getString(columnName))
                    .collect(Collectors.toList());
        } catch (Sql2oException e) {
            throw new DatabaseQueryException("Error executing query and adding data to list", e);
        } finally {
            DatabaseConnectionUtil.closeConnection();
        }
    }

}
