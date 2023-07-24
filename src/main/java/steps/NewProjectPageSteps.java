package steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import org.testng.Assert;
import pages.NewProjectPage;

public class NewProjectPageSteps {
    private static final Logger logger = AqualityServices.getLogger();
    private static final NewProjectPage newProjectPage = new NewProjectPage();

    private NewProjectPageSteps() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", getClass().getSimpleName()));
    }

    public static void verifyNewTestCreated(String testName) {
        logger.info("Checking that new test created");
        AqualityServices.getConditionalWait().waitFor(() -> newProjectPage.isTestNameVisible(testName));
        Assert.assertTrue(newProjectPage.isTestNameVisible(testName), "Web table is empty");
    }

    public static void openTestByName(String testName) {
        newProjectPage.clickNewTest(testName);
    }
}
