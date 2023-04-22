package constants;

import util.JsonDataManagerUtil;

public class ConfigData {
    public static final String WEBSITE_BASE_URL = JsonDataManagerUtil.getStringElementByKey(DataPaths.CONFIG_DATA_PATH, "websiteBaseUrl");
    public static final String API_REQUEST_BASE_URL = JsonDataManagerUtil.getStringElementByKey(DataPaths.CONFIG_DATA_PATH, "apiRequestBaseUrl");

    private ConfigData() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s should not be initialized", ConfigData.class.getCanonicalName()));
    }
}
