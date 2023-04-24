package steps;

import constants.TestData;
import org.testng.Assert;
import utils.ApiUtil;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiSteps {
    public static final String TOKEN = ApiUtil.getToken(TestData.VARIANT_NUMBER).asString();

    public static String getToken() {
        return TOKEN;
    }

    public static void verifyToken() {
        Assert.assertTrue(TOKEN != null && !TOKEN.isEmpty(), "Token is not generated");
    }
}
