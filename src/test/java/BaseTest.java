import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import constants.ConfigData;
import constants.TestData;
import models.database.TestTableRecord;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    private final Logger logger = AqualityServices.getLogger();
    private final Browser browser = AqualityServices.getBrowser();

    @BeforeMethod
    protected void beforeTest() {
        logger.info("Authorizing with Basic Authentication");
        browser.network().addBasicAuthentication(ConfigData.WEB_APP_HOST, TestData.USER, TestData.PASSWORD);
        browser.maximize();
        browser.goTo(ConfigData.WEB_APP_BASE_URL);
        browser.waitForPageToLoad();
    }

    @AfterMethod
    protected void afterTest() {
        logger.info("Ending test");
        if (AqualityServices.isBrowserStarted()) {
            browser.quit();
        }
    }
}
