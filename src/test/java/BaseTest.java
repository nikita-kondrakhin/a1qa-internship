import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import constants.ConfigData;
import constants.TestData;
import models.database.TestTable;
import models.webapp.Test;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import utils.LogUtil;

public abstract class BaseTest {
    private final Logger logger = AqualityServices.getLogger();
    private final Browser browser = AqualityServices.getBrowser();
    protected TestTable testTable;

    @BeforeMethod
    protected void beforeTest() {
//        LogUtil.deleteLogFile(); //todo
//        ITestResult result = Reporter.getCurrentTestResult();
//        System.out.println(result);
//        testTable.setName(result.getTestName());
//        testTable.setMethodName(result.getMethod());

        logger.info("Authorizing with Basic Authentication");
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
