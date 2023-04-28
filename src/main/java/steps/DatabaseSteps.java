package steps;

import constants.DataPaths;
import utils.database.DatabaseQueryUtil;

import java.util.List;

public class DatabaseSteps {
    private DatabaseSteps() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", DatabaseSteps.class.getCanonicalName())); //todo getCanonicalName?
    }

    public static List<String> getTestNamesFromDatabase() {
        String query = DatabaseQueryUtil.readQueryFromFile(DataPaths.SELECT_TESTS_DESC_BY_START_TIME_QUERY_PATH);
        return DatabaseQueryUtil.executeQueryAndAddTestNamesToList(query);
    }
}
