package steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import constants.Queries;
import constants.TestData;
import models.database.LogTableRecord;
import models.database.TestTableRecord;
import utils.LogUtil;
import utils.database.DatabaseQueryUtil;

import java.util.List;

public class DatabaseSteps {
    private static final Logger logger = AqualityServices.getLogger();
    private static final int STATUS_ID = 1;
    private static final int SESSION_ID = 1;
    private static final String ENV = "Nikita";
    private static final int IS_EXCEPTION = 1;



    private DatabaseSteps() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", getClass().getSimpleName()));
    }

    public static List<String> getTestNamesFromDatabase() {
        logger.info("Retrieving test names from database");
        String query = DatabaseQueryUtil.readQueryFromFile(Queries.SELECT_TEST_NAMES_QUERY);
        return DatabaseQueryUtil.getDatabaseColumnValues(query, TestData.COLUMN_NAME);
    }

    public static void addTestToDatabase(String projectName, String testName, String methodName, String testStartTime, String testEndTime) {
        int projectRecordId = getProjectRecordId(projectName);
        int testRecordId = createTestRecordAndGetId(testName, methodName, projectRecordId, testStartTime, testEndTime);
        createLogRecord(testRecordId, LogUtil.getLog());
    }

    private static int getProjectRecordId(String projectName) {
        logger.info("Retrieving project record id from database");
        String selectProjectIdQuery = DatabaseQueryUtil.readQueryFromFile(Queries.SELECT_PROJECT_ID_QUERY);
        return DatabaseQueryUtil.getProjectIdByName(projectName, selectProjectIdQuery);
    }

    private static int createTestRecordAndGetId(String testName, String methodName, int projectRecordId, String testStartTime, String testEndTime) {
        logger.info("Creating test record in database and retrieving test id");
        TestTableRecord testTableRecord = TestTableRecord.builder()
                .name(testName)
                .statusId(STATUS_ID)
                .methodName(methodName)
                .projectId(projectRecordId)
                .sessionId(SESSION_ID)
                .startTime(testStartTime)
                .endTime(testEndTime)
                .env(ENV)
                .browser(AqualityServices.getBrowser().getBrowserName().name())
                .authorId(null)
                .build();

        String insertTestQuery = DatabaseQueryUtil.readQueryFromFile(Queries.INSERT_TEST_QUERY);
        return DatabaseQueryUtil.createRecordAndGetId(insertTestQuery, testTableRecord);
    }

    private static void createLogRecord(int testRecordId, String log) {
        logger.info("Creating log record in database");
        LogTableRecord logTableRecord = LogTableRecord.builder()
                .content(log)
                .isException(IS_EXCEPTION)
                .testId(testRecordId)
                .build();

        String insertLogQuery = DatabaseQueryUtil.readQueryFromFile(Queries.INSERT_LOG_QUERY);
        DatabaseQueryUtil.createRecord(insertLogQuery, logTableRecord);
    }
}
