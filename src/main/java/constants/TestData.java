package constants;

import utils.JsonDataManagerUtil;

public class TestData {
    public static final int VARIANT_NUMBER = JsonDataManagerUtil.getIntElementByKey(DataPaths.TEST_DATA_PATH, "variantNumber");
    public static final String USER = JsonDataManagerUtil.getStringElementByKey(DataPaths.TEST_DATA_PATH, "user");
    public static final String PASSWORD = JsonDataManagerUtil.getStringElementByKey(DataPaths.TEST_DATA_PATH, "password");

    private TestData() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s should not be initialized", TestData.class.getCanonicalName()));
    }
}
