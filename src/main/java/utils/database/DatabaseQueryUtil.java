package utils.database;

import constants.WebTableColumnNames;
import exceptions.DatabaseQueryException;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.ResultSetIterable;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class DatabaseQueryUtil {
    public static String readQueryFromFile(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            throw new DatabaseQueryException("Can't read query from file", e);
        }
    }

//    public static List<String> executeQueryAndAddTestNamesToList(String query) {
//        try (Connection connection = DatabaseConnectionUtil.getConnection();
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(query)) {
//            List<String> testNames = new ArrayList<>();
//            while (resultSet.next()) {
//                String testName = resultSet.getString(WebTableColumnNames.NAME_COLUMN.getColumnName());
//                if (testName != null && !testName.isEmpty()) {
//                    testNames.add(testName);
//                }
//            }
//            return testNames;
//        } catch (SQLException e) {
//            throw new DatabaseQueryException("Error executing query and adding data to list", e);
//        }
//    }

    public static List<String> executeQueryAndAddTestNamesToList(String query) {
        try (Connection connection = DatabaseConnectionUtil.getConnection()) {
            List<String> testNames = connection.createQuery(query)
                    .executeAndFetch((ResultSet resultSet) -> {
                        List<String> names = new ArrayList<>();
                        while (resultSet.next()) {
                            String testName = resultSet.getString(WebTableColumnNames.NAME_COLUMN.getColumnName());
                            if (testName != null && !testName.isEmpty()) {
                                names.add(testName);
                            }
                        }
                        return names;
                    });
            return testNames;
        } catch (SQLException e) {
            throw new DatabaseQueryException("Error executing query and adding data to list", e);
        }
    }

}
