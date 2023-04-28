package steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import constants.CookieData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import utils.ApiUtil;
import utils.BrowserActionsUtil;

public class AuthenticationSteps {
    private static final Logger logger = AqualityServices.getLogger();
    private static String token;

    private AuthenticationSteps() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", getClass().getSimpleName()));
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

    public static void addCookieWithToken(String token) {
        logger.info(String.format("Adding cookie with previously generated token '%s'", token));
        Cookie cookie = new Cookie(CookieData.TOKEN_PARAMETER, token);
        BrowserActionsUtil.addCookie(cookie);
    }
}
