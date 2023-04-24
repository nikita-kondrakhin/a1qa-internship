package constants;

import utils.JsonDataManagerUtil;

public class CookieData {
    public static final String COOKIE_PARAMETER = JsonDataManagerUtil.getStringElementByKey(DataPaths.COOKIE_DATA_PATH, "cookieParameter");

    private CookieData() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s should not be initialized", CookieData.class.getCanonicalName()));
    }
}
