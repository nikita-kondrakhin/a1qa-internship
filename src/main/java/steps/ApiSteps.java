package steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import utils.ApiUtil;

public class ApiSteps {
    private static String token;

    public static String getToken(int variantNumber) {
        Response getToken = ApiUtil.getToken(variantNumber);
        ApiUtil.checkResponseContentType(getToken, ContentType.TEXT);
        ApiUtil.checkResponseStatusCode(getToken, HttpStatus.SC_OK);
        token = getToken.asString();
        return token;
    }

    public static void verifyToken() {
        Assert.assertTrue(token != null && !token.isEmpty(), "Token is not generated");
    }
}
