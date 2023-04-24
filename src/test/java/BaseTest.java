import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import constants.ConfigData;
import constants.TestData;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    @BeforeMethod
    protected void beforeTest() {
        Browser browser = AqualityServices.getBrowser();
        browser.network().addBasicAuthentication(ConfigData.WEB_APP_HOST, TestData.USER, TestData.PASSWORD);
        browser.maximize();
        browser.goTo(ConfigData.WEB_APP_BASE_URL);
        browser.waitForPageToLoad();
    }

//    @AfterMethod //todo
//    protected void afterTest() {
//        if (AqualityServices.isBrowserStarted()) {
//            AqualityServices.getBrowser().quit();
//        }
//    }
}
