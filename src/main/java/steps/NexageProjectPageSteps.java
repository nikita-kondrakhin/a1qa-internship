package steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import org.testng.Assert;
import pages.NexageProjectPage;
import utils.BrowserActionsUtil;

public class NexageProjectPageSteps {
    private static final Logger logger = AqualityServices.getLogger();
    private static final NexageProjectPage nexageProjectPage = new NexageProjectPage();

    private NexageProjectPageSteps() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", NexageProjectPageSteps.class.getCanonicalName()));
    }

    public static void verifyNexageProjectPageIsOpen() {
        logger.info(String.format("Checking if %s is open", nexageProjectPage.getName()));
        Assert.assertTrue(nexageProjectPage.state().isDisplayed(), String.format("%s is not open", nexageProjectPage.getName()));
    }

    public static void goBackToProjectsPage() {
        BrowserActionsUtil.goBack();
    }

    public static void getTestsFromPage() {

    }
}
