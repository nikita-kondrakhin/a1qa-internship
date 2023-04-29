package steps;

import constants.DataPaths;
import constants.TestData;
import models.database.AttachmentTable;
import models.database.LogTable;
import models.database.TestTable;
import utils.BrowserActionsUtil;
import utils.database.DatabaseQueryUtil;

import java.util.Base64;
import java.util.List;

public class DatabaseSteps {
    private DatabaseSteps() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", getClass().getSimpleName()));
    }

    public static List<String> getTestNamesFromDatabase() {
        String query = DatabaseQueryUtil.readQueryFromFile(DataPaths.SELECT_TEST_NAMES_QUERY);
        return DatabaseQueryUtil.getDatabaseColumnValues(query, TestData.COLUMN_NAME);
    }

    public static void addTestToDatabase(String testNam) {



//        try (Connection connection = DatabaseConnectionUtil.getConnection()) {
//            connection.getJdbcConnection().setAutoCommit(false);
//
//            // Perform multiple database operations here using conn
//
//            connection.getJdbcConnection().commit();
//        } catch (Exception e) {
//            // Handle any exceptions that occurred during the transaction
//            .getJdbcConnection().rollback();
//        } finally {
//            conn.getJdbcConnection().setAutoCommit(true);
//        }
//
//
//        try (Connection connection = DatabaseConnectionUtil.getConnection()) {
//            // Begin transaction
//            connection.begin();
//
//            // Insert record into the test table
//            TestDatabase test = new TestDatabase();
//            test.setName("My Test");
//            // set other fields
//            int testId = createRecord("INSERT INTO test(name, status_id, method_name, project_id, session_id, start_time, end_time, env, browser, author_id) VALUES (:name, :statusId, :methodName, :projectId, :sessionId, :startTime, :endTime, :env, :browser, :authorId)", test);
//
//            // Insert record into the attachment table
//            AttachmentTable attachment = new AttachmentTable();
//            attachment.setContent(new File("screenshot.png"));
//            attachment.setContentType("image/png");
//            attachment.setTestId(testId);
//            createRecord("INSERT INTO attachment(content, content_type, test_id) VALUES (:content, :contentType, :testId)", attachment);
//
//            // Insert record into the log table
//            LogTable log = new LogTable();
//            log.setContent("Log content");
//            log.setIsException(0);
//            log.setTestId(testId);
//            createRecord("INSERT INTO log(content, is_exception, test_id) VALUES (:content, :isException, :testId)", log);
//
//            // Commit transaction
//            connection.commit();
//        } catch (DatabaseQueryException e) {
//            // Handle exception
//        }
//
//        int testId =
    }

    private static void addScreenshot(int testId) {
        DatabaseQueryUtil.insertAttachmentIntoDatabase(DataPaths.INSERT_ATTACHMENT_QUERY, BrowserActionsUtil.getWebpageScreenshot(), testId);
    }

    public static void addTestToDatabase(String testName, String testStartTime, String testEndTime, String log) {
        TestTable testTable = new TestTable(
                testName,
                1,
                "Method name",
                1,
                1,
                testStartTime,
                testEndTime,
                "Test env",
                "Test browser",
                null);

        String insertTestQuery = DatabaseQueryUtil.readQueryFromFile(DataPaths.INSERT_TEST_QUERY);
        int testId = DatabaseQueryUtil.createRecordAndGetId(insertTestQuery, testTable);
        System.out.println(testId);

        LogTable logTable = new LogTable();
        logTable.setContent(log);
        logTable.setIsException(0);
        logTable.setTestId(testId);

        String insertLogQuery = DatabaseQueryUtil.readQueryFromFile(DataPaths.INSERT_LOG_QUERY);
        int logId = DatabaseQueryUtil.createRecordAndGetId(insertLogQuery, logTable);
        System.out.println(logId);

        AttachmentTable attachmentTable = new AttachmentTable();
        attachmentTable.setContent(BrowserActionsUtil.getWebpageScreenshot());
        attachmentTable.setContentType("image/png");
        attachmentTable.setTestId(testId);

        String insertAttachmentQuery = DatabaseQueryUtil.readQueryFromFile(DataPaths.INSERT_ATTACHMENT_QUERY);
        DatabaseQueryUtil.insertAttachmentIntoDatabase(insertAttachmentQuery, attachmentTable.getContent(), testId);


    }
}
