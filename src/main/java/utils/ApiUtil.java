package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import constants.ApiData;
import constants.ConfigData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

import static io.restassured.RestAssured.given;

@UtilityClass
public class ApiUtil {
    private static final Logger logger = AqualityServices.getLogger();

    public static Response getToken(int variantNumber) {
        logger.info(String.format("Sending API request to get token for task variant %d", variantNumber));
        return given()
                .baseUri(ConfigData.API_REQUEST_BASE_URL)
                .queryParam(ApiData.VARIANT_QUERY_PARAM, variantNumber)
                .post(ApiData.GET_TOKEN_ENDPOINT);
    }

    public static void checkResponseContentType(Response response, ContentType contentType) {
        logger.info(String.format("Checking that response is %s", contentType.toString()));
        response
                .then()
                .assertThat()
                .contentType(contentType);
    }

    public static void checkResponseStatusCode(Response response, int code) {
        logger.info(String.format("Checking that response status code is '%s'", code));
        response
                .then()
                .assertThat()
                .statusCode(code);
    }
}
