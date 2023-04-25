package constants;

import utils.JsonDataManagerUtil;

public class CookieData {
    public static final String TOKEN_PARAMETER = JsonDataManagerUtil.getStringElementByKey(DataPaths.COOKIE_DATA_PATH, "tokenParameter");

    private CookieData() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", CookieData.class.getCanonicalName()));
    }
}
