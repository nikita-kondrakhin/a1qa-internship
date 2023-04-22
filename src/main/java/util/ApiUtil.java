package util;

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
        logger.info(String.format("Sending API request to get token for task variant #%d", variantNumber));
        return given()
                .baseUri(ConfigData.API_REQUEST_BASE_URL)
                .contentType(ContentType.JSON)
                .queryParam(ApiData.VARIANT_QUERY_PARAM, variantNumber)
                .post(ApiData.GET_TOKEN_ENDPOINT);
    }
}
