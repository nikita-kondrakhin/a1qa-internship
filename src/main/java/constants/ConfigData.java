package constants;

import utils.JsonDataManagerUtil;

public class ConfigData {
    public static final String WEB_APP_HOST = JsonDataManagerUtil.getStringElementByKey(DataPaths.CONFIG_DATA_PATH, "webAppHost");
    public static final String WEB_APP_BASE_URL = JsonDataManagerUtil.getStringElementByKey(DataPaths.CONFIG_DATA_PATH, "webAppBaseUrl");
    public static final String API_REQUEST_BASE_URL = JsonDataManagerUtil.getStringElementByKey(DataPaths.CONFIG_DATA_PATH, "apiRequestBaseUrl");
    public static final String DB_CONNECTION_URL = JsonDataManagerUtil.getStringElementByKey(DataPaths.CONFIG_DATA_PATH, "dbConnectionUrl");

    private ConfigData() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", ConfigData.class.getCanonicalName()));
    }
}
