import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import constants.ConfigData;
import constants.TestData;
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

    @AfterMethod //todo
    protected void afterTest() {
        logger.info("Finishing test"); //todo
        if (AqualityServices.isBrowserStarted()) {
            browser.quit();
        }
    }
}
