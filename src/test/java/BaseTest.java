import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import constants.ConfigData;
import constants.TestData;
import models.Test;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.LogUtil;
import utils.TimeUtil;

import java.net.MalformedURLException;

public abstract class BaseTest {
    private final Logger logger = AqualityServices.getLogger();
    private final Browser browser = AqualityServices.getBrowser();
    private Test test;

    @BeforeMethod
    protected void beforeTest() {
//        LogUtil.deleteLogFile(); todo
        logger.info("Authorizing with Basic Authentication");
//        test.setLatestTestStartTime(TimeUtil.getCurrentTime());
//        test.setTestName(Reporter.getCurrentTestResult().getInstanceName());
        browser.network().addBasicAuthentication(ConfigData.WEB_APP_HOST, TestData.USER, TestData.PASSWORD);
        browser.maximize();
        browser.goTo(ConfigData.WEB_APP_BASE_URL);
        browser.waitForPageToLoad();
    }

//    @AfterMethod //todo
//    protected void afterTest() {
//        logger.info("Ending test");
//        if (AqualityServices.isBrowserStarted()) {
//            browser.quit();
//        }
//    }
}
