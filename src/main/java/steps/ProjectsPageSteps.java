package steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import constants.TestData;
import org.testng.Assert;
import pages.AllProjectsPage;
import utils.BrowserActionsUtil;
import utils.ExtractNumberUtil;

public class ProjectsPageSteps {
    private static final Logger logger = AqualityServices.getLogger();
    private static final AllProjectsPage allProjectsPage = new AllProjectsPage();

    private ProjectsPageSteps() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", getClass().getSimpleName()));
    }

    public static void verifyProjectsPageIsOpen() {
        logger.info(String.format("Checking that %s is open", allProjectsPage.getName()));
        Assert.assertTrue(allProjectsPage.state().isDisplayed(), String.format("%s is not open", allProjectsPage.getName()));
    }

    public static void refreshPage() {
        BrowserActionsUtil.refresh();
    }

    public static void verifyVariant() {
        logger.info("Checking that variant number in web application footer is correct");
        int actualVariantNumber = ExtractNumberUtil.getVariantNumber(allProjectsPage.getVersionLabel());
        Assert.assertEquals(actualVariantNumber, TestData.VARIANT_NUMBER, "Variant number is not correct");
    }

    public static void goToNexageProjectPage() {
        logger.info("Going to Nexage project page");
        allProjectsPage.clickNexageButton();
    }

    public static void openAddProjectForm() {
        logger.info("Opening Add project form");
        allProjectsPage.clickAddButton();
    }

    public static void verifyProjectAppearedInList(String projectName) {
        logger.info(String.format("Checking that project '%s' added to projects list", projectName));
        Assert.assertTrue(allProjectsPage.isProjectAdded(projectName), String.format("'%s' project is not appeared in projects list", projectName));
    }

    public static void openProjectByName(String projectName) {
        allProjectsPage.clickNewProject(projectName);
    }
}
