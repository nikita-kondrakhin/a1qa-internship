package constants;

import utils.JsonDataManagerUtil;

public class ConfigData {
    public static final String WEB_APP_HOST = JsonDataManagerUtil.getStringElementByKey(JsonDataPaths.CONFIG_DATA_PATH, "webAppHost");
    public static final String WEB_APP_BASE_URL = JsonDataManagerUtil.getStringElementByKey(JsonDataPaths.CONFIG_DATA_PATH, "webAppBaseUrl");
    public static final String API_REQUEST_BASE_URL = JsonDataManagerUtil.getStringElementByKey(JsonDataPaths.CONFIG_DATA_PATH, "apiRequestBaseUrl");
    public static final String DB_CONNECTION_URL = JsonDataManagerUtil.getStringElementByKey(JsonDataPaths.CONFIG_DATA_PATH, "dbConnectionUrl");
    public static final String TIME_FORMAT = JsonDataManagerUtil.getStringElementByKey(JsonDataPaths.CONFIG_DATA_PATH, "timeFormat");
    public static final String LOG_FILE_PATH = JsonDataManagerUtil.getStringElementByKey(JsonDataPaths.CONFIG_DATA_PATH, "logFilePath");

    private ConfigData() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", getClass().getSimpleName()));
    }
}
