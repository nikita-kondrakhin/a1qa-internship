package constants;

public class DataPaths {
    private static final String JSON_DATA_DIRECTORY_PATH = ".\\src\\test\\resources\\%s";
    public static final String CONFIG_DATA_PATH = String.format(JSON_DATA_DIRECTORY_PATH, "config-data.json");
    public static final String TEST_DATA_PATH = String.format(JSON_DATA_DIRECTORY_PATH, "test-data.json");
    public static final String API_DATA_PATH = String.format(JSON_DATA_DIRECTORY_PATH, "api-data.json");
    public static final String COOKIE_DATA_PATH = String.format(JSON_DATA_DIRECTORY_PATH, "cookie-data.json");
}
