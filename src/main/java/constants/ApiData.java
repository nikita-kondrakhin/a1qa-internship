package constants;

import utils.JsonDataManagerUtil;

public class ApiData {
    public static final String GET_TOKEN_ENDPOINT = JsonDataManagerUtil.getStringElementByKey(DataPaths.API_DATA_PATH, "getTokenEndpoint");
    public static final String VARIANT_QUERY_PARAM = JsonDataManagerUtil.getStringElementByKey(DataPaths.API_DATA_PATH, "variantQueryParam");

    private ApiData() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s should not be initialized", ConfigData.class.getCanonicalName()));
    }
}
