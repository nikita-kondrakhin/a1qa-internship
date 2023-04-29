package constants;

import utils.JsonDataManagerUtil;

public class ApiData {
    public static final String GET_TOKEN_ENDPOINT = JsonDataManagerUtil.getStringElementByKey(JsonDataPaths.API_DATA_PATH, "getTokenEndpoint");
    public static final String VARIANT_QUERY_PARAM = JsonDataManagerUtil.getStringElementByKey(JsonDataPaths.API_DATA_PATH, "variantQueryParam");

    private ApiData() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", getClass().getSimpleName()));
    }
}
