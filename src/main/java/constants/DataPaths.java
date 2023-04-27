package constants;

public class DataPaths {
    private static final String JSON_DATA_DIRECTORY_PATH = ".\\src\\test\\resources\\%s";
    private static final String SQL_DATA_DIRECTORY_PATH = ".\\src\\test\\resources\\sql-queries\\%s";
    public static final String CONFIG_DATA_PATH = String.format(JSON_DATA_DIRECTORY_PATH, "config-data.json");
    public static final String TEST_DATA_PATH = String.format(JSON_DATA_DIRECTORY_PATH, "test-data.json");
    public static final String API_DATA_PATH = String.format(JSON_DATA_DIRECTORY_PATH, "api-data.json");
    public static final String COOKIE_DATA_PATH = String.format(JSON_DATA_DIRECTORY_PATH, "cookie-data.json");
    public static final String JS_DATA_PATH = String.format(JSON_DATA_DIRECTORY_PATH, "js-data.json");
    public static final String SELECT_TESTS_DESC_BY_START_TIME_QUERY_PATH = String.format(SQL_DATA_DIRECTORY_PATH, "selectTestsDescByStartTime.sql");

    private DataPaths() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", DataPaths.class.getCanonicalName())); //todo getCanonicalName?
    }
}
