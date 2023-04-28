package constants;

import utils.JsonDataManagerUtil;

public class TestData {
    public static final int VARIANT_NUMBER = JsonDataManagerUtil.getIntElementByKey(DataPaths.TEST_DATA_PATH, "variantNumber");
    public static final String USER = JsonDataManagerUtil.getStringElementByKey(DataPaths.TEST_DATA_PATH, "user");
    public static final String PASSWORD = JsonDataManagerUtil.getStringElementByKey(DataPaths.TEST_DATA_PATH, "password");
    public static final String NEW_PROJECT_NAME = JsonDataManagerUtil.getStringElementByKey(DataPaths.TEST_DATA_PATH, "newProjectName");
    public static final String NEW_TEST_NAME = JsonDataManagerUtil.getStringElementByKey(DataPaths.TEST_DATA_PATH, "newTestName");
    public static final int RANDOM_STRING_LENGTH = JsonDataManagerUtil.getIntElementByKey(DataPaths.TEST_DATA_PATH, "randomStringLength");
    public static final String IFRAME_ID = JsonDataManagerUtil.getStringElementByKey(DataPaths.TEST_DATA_PATH, "iframeId");
    public static final String COLUMN_NAME = JsonDataManagerUtil.getStringElementByKey(DataPaths.TEST_DATA_PATH, "columnName");

    private TestData() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", getClass().getSimpleName()));
    }
}
