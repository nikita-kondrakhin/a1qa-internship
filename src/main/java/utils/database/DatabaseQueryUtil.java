package utils.database;

import exceptions.DatabaseQueryException;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

import models.database.BaseTable;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2oException;
import org.sql2o.converters.ByteArrayConverter;
import org.sql2o.data.Table;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DatabaseQueryUtil {
    private static Connection connection = DatabaseConnectionUtil.getConnection();

    public static String readQueryFromFile(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            throw new DatabaseQueryException("Can't read query from file", e);
        }
    }

    public static List<String> getDatabaseColumnValues(String query, String columnName) {
        try {
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

    public static void insertAttachmentIntoDatabase(String query, byte[] bytes, int testId) {
        try {
            Query sql2oQuery = connection.createQuery(query)
                    .addParameter("content", bytes, java.sql.Types.BLOB)
                    .addParameter("content_type", "image/png") //todo hardcode
                    .addParameter("test_id", testId);
            sql2oQuery.executeUpdate();
        } catch (Sql2oException e) {
            throw new DatabaseQueryException("Error inserting image into database", e);
        } finally {
            DatabaseConnectionUtil.closeConnection();
        }
    }

    public static <T extends BaseTable> int createRecordAndGetId(String request, T tModel) {
        try {
            BigInteger key = (BigInteger) connection.createQuery(request)
                    .bind(tModel)
                    .executeUpdate()
                    .getKey();
            if (key == null) {
                throw new DatabaseQueryException("No auto-generated key was returned after executing the insert query");
            }
            return key.intValueExact();
        } catch (Sql2oException e) {
            throw new DatabaseQueryException("Error executing insert query", e);
        } finally {
            DatabaseConnectionUtil.closeConnection();
        }
    }
}
