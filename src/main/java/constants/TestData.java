package constants;

import util.JsonDataManagerUtil;

public class TestData {
    public static final int VARIANT_NUMBER = JsonDataManagerUtil.getIntElementByKey(DataPaths.TEST_DATA_PATH, "variantNumber");

    private TestData() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s should not be initialized", TestData.class.getCanonicalName()));
    }
}
