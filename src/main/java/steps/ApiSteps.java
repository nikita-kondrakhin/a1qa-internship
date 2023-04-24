package steps;

import constants.TestData;
import utils.ApiUtil;

public class ApiSteps {
    public static String getAuthorizationToken() {
        ApiUtil.checkResponseIsText(ApiUtil.getToken(TestData.VARIANT_NUMBER));
        return ApiUtil.getToken(TestData.VARIANT_NUMBER).asString();
    }
}
