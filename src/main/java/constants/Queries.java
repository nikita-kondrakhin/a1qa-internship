package constants;

import java.io.File;

public class Queries {
    private static final String SQL_DATA_DIRECTORY_PATH = "." + File.separator
            + "src" + File.separator
            + "test" + File.separator
            + "resources" + File.separator
            + "sql-queries" + File.separator
            + "%s";

    public static final String SELECT_TEST_NAMES_QUERY = String.format(SQL_DATA_DIRECTORY_PATH, "selectTestNames.sql");
    public static final String INSERT_TEST_QUERY = String.format(SQL_DATA_DIRECTORY_PATH, "insertTest.sql");
    public static final String INSERT_ATTACHMENT_QUERY = String.format(SQL_DATA_DIRECTORY_PATH, "insertAttachment.sql");
    public static final String INSERT_LOG_QUERY = String.format(SQL_DATA_DIRECTORY_PATH, "insertLog.sql");
    public static final String SELECT_PROJECT_ID_QUERY = String.format(SQL_DATA_DIRECTORY_PATH, "selectProjectId.sql");

    private Queries() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", getClass().getSimpleName()));
    }
}
