package steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import utils.ApiUtil;

public class ApiSteps {
    private static final Logger logger = AqualityServices.getLogger();
    private static String token;

    private ApiSteps() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", ApiSteps.class.getCanonicalName())); //todo getCanonicalName?
    }

    public static String getToken(int variantNumber) {
        Response getToken = ApiUtil.getToken(variantNumber);
        ApiUtil.checkResponseContentType(getToken, ContentType.TEXT);
        ApiUtil.checkResponseStatusCode(getToken, HttpStatus.SC_OK);
        token = getToken.asString();
        return token;
    }

    public static void verifyToken() {
        logger.info("Checking that token is generated");
        Assert.assertTrue(token != null && !token.isEmpty(), "Token is not generated");
    }
}
