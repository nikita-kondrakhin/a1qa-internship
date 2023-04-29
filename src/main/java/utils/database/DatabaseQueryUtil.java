package utils.database;

import exceptions.DatabaseQueryException;
import lombok.experimental.UtilityClass;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

import models.database.BaseTableRecord;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;
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

    public Integer getProjectIdByName(String name, String query) {
        try {
            return connection.createQuery(query)
                    .addParameter("name", name)
                    .executeScalar(Integer.class);
        } catch (Sql2oException ex) {
            throw new DatabaseQueryException("Error retrieving project id by name", ex);
        } finally {
            DatabaseConnectionUtil.closeConnection();
        }
    }

    public static <T extends BaseTableRecord> int createRecordAndGetId(String query, T tModel) {
        try {
            BigInteger key = (BigInteger) connection.createQuery(query)
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

    public static <T extends BaseTableRecord> void createRecord(String query, T tModel) {
        try {
            connection.createQuery(query)
                    .bind(tModel)
                    .executeUpdate();
        } catch (Sql2oException e) {
            throw new DatabaseQueryException("Error executing insert query", e);
        } finally {
            DatabaseConnectionUtil.closeConnection();
        }
    }

    public static void insertAttachmentIntoDatabase(String query, byte[] bytes, int testId) {
        try {
            connection.createQuery(query)
                    .addParameter("content", new ByteArrayInputStream(bytes), bytes.length)
                    .addParameter("content_type", "image/png") //todo hardcode
                    .addParameter("test_id", testId)
                    .executeUpdate();
        } catch (Sql2oException e) {
            throw new DatabaseQueryException("Error inserting image into database", e);
        } finally {
            DatabaseConnectionUtil.closeConnection();
        }
    }
}
