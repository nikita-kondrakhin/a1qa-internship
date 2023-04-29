package steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import constants.Queries;
import constants.TestData;
import models.database.AttachmentTableRecord;
import models.database.LogTableRecord;
import models.database.TestTableRecord;
import utils.BrowserActionsUtil;
import utils.LogUtil;
import utils.database.DatabaseQueryUtil;

import java.util.List;

public class DatabaseSteps {
    private static final Logger logger = AqualityServices.getLogger();

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
        createAttachmentRecord(testRecordId, BrowserActionsUtil.getWebpageScreenshot());
    }

    private static int getProjectRecordId(String projectName) {
        logger.info("Retrieving project record id from database");
        String selectProjectIdQuery = DatabaseQueryUtil.readQueryFromFile(Queries.SELECT_PROJECT_ID_QUERY);
        return DatabaseQueryUtil.getProjectIdByName(projectName, selectProjectIdQuery);
    }

    private static int createTestRecordAndGetId(String testName, String methodName, int projectRecordId, String testStartTime, String testEndTime) {
        logger.info("Creating test record in database and retrieving test id");
        TestTableRecord testTableRecord = new TestTableRecord();
        testTableRecord.setName(testName);
        testTableRecord.setStatusId(1);
        testTableRecord.setMethodName(methodName);
        testTableRecord.setProjectId(projectRecordId);
        testTableRecord.setSessionId(1);
        testTableRecord.setStartTime(testStartTime);
        testTableRecord.setEndTime(testEndTime);
        testTableRecord.setEnv("WebTableRecord env");//todo hardcode
        testTableRecord.setBrowser("WebTableRecord browser");
        testTableRecord.setAuthorId(null);

        String insertTestQuery = DatabaseQueryUtil.readQueryFromFile(Queries.INSERT_TEST_QUERY);
        return DatabaseQueryUtil.createRecordAndGetId(insertTestQuery, testTableRecord);
    }

    private static void createLogRecord(int testRecordId, String log) {
        logger.info("Creating log record in database");
        LogTableRecord logTableRecord = new LogTableRecord();
        logTableRecord.setContent(log);
        logTableRecord.setIsException(0);
        logTableRecord.setTestId(testRecordId);

        String insertLogQuery = DatabaseQueryUtil.readQueryFromFile(Queries.INSERT_LOG_QUERY);
        DatabaseQueryUtil.createRecord(insertLogQuery, logTableRecord);
    }

    private static void createAttachmentRecord(int testRecordId, byte[] attachment) {
        logger.info("Creating attachment record in database");
        AttachmentTableRecord attachmentTableRecord = new AttachmentTableRecord();
        attachmentTableRecord.setContent(attachment);
        attachmentTableRecord.setContentType("null");
        attachmentTableRecord.setTestId(testRecordId);

        String insertAttachmentQuery = DatabaseQueryUtil.readQueryFromFile(Queries.INSERT_ATTACHMENT_QUERY);
        DatabaseQueryUtil.insertAttachmentIntoDatabase(insertAttachmentQuery, attachment, testRecordId);
//        DatabaseQueryUtil.createRecord(insertAttachmentQuery, attachmentTableRecord);
    }
}
