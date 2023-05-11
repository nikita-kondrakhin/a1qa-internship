package constants;

import java.io.File;

public class JsonDataPaths {
    private static final String JSON_DATA_DIRECTORY_PATH = "." + File.separator
            + "src" + File.separator
            + "test" + File.separator
            + "resources" + File.separator
            + "%s";
    public static final String CONFIG_DATA_PATH = String.format(JSON_DATA_DIRECTORY_PATH, "config-data.json");
    public static final String TEST_DATA_PATH = String.format(JSON_DATA_DIRECTORY_PATH, "test-data.json");
    public static final String API_DATA_PATH = String.format(JSON_DATA_DIRECTORY_PATH, "api-data.json");
    public static final String COOKIE_DATA_PATH = String.format(JSON_DATA_DIRECTORY_PATH, "cookie-data.json");
    public static final String JS_DATA_PATH = String.format(JSON_DATA_DIRECTORY_PATH, "js-data.json");

    private JsonDataPaths() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", getClass().getSimpleName()));
    }
}
