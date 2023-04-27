package steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import constants.TestData;
import org.testng.Assert;
import pages.ProjectsPage;
import utils.BrowserActionsUtil;
import utils.ExtractNumberUtil;

public class ProjectsPageSteps {
    private static final Logger logger = AqualityServices.getLogger();
    private static final ProjectsPage projectsPage = new ProjectsPage();

    private ProjectsPageSteps() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", ProjectsPageSteps.class.getCanonicalName()));
    }

    public static void verifyProjectsPageIsOpen() {
        logger.info(String.format("Checking that %s is open", projectsPage.getName()));
        Assert.assertTrue(projectsPage.state().isDisplayed(), String.format("%s is not open", projectsPage.getName()));
    }

    public static void refreshPage() {
        BrowserActionsUtil.refresh();
    }

    public static void verifyVariant() {
        logger.info("Checking that variant number in web application footer is correct");
        int actualVariantNumber = ExtractNumberUtil.getVariantNumber(projectsPage.getVersionLabel());
        Assert.assertEquals(actualVariantNumber, TestData.VARIANT_NUMBER, "Variant number is not correct");
    }

    public static void goToNexageProjectPage() {
        logger.info("Going to Nexage project page");
        projectsPage.clickNexageButton();
    }

    public static void openAddProjectForm() {
        logger.info("Opening Add project form"); //todo open duplication, decide to use 'checking if' or 'checking that'
        projectsPage.clickAddButton();
    }

    public static void verifyProjectAppearedInList(String projectName) {
        logger.info(String.format("Checking that project '%s' added to projects list", projectName));
        Assert.assertTrue(projectsPage.isProjectAdded(projectName), String.format("'%s' project is not appeared in projects list", projectName));
    }

    public static void openProjectByName(String projectName) {
        projectsPage.clickNewProject(projectName);
    }
}
