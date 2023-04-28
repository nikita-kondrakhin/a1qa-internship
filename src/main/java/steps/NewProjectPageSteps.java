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

//    public static void verifyNewProjectPageIsOpen() {
//        logger.info(String.format("Checking that %s is open", newProjectPage.getName()));
//        Assert.assertTrue(newProjectPage.state().isDisplayed(), String.format("%s is not open", newProjectPage.getName()));
//    }
}
