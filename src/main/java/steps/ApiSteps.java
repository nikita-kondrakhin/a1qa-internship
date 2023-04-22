package steps;

import constants.TestData;
import util.ApiUtil;

public class ApiSteps {
    public static void getApiToken() {
        ApiUtil.getToken(TestData.VARIANT_NUMBER);
    }
}
