package steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import constants.CookieData;
import constants.TestData;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import pages.AddProjectForm;
import pages.NexageProjectPage;
import pages.ProjectsPage;
import utils.BrowserActionsUtil;
import utils.ExtractNumberUtil;

public class UiSteps {
    private static final Logger logger = AqualityServices.getLogger();
    private static final ProjectsPage projectsPage = new ProjectsPage();
    private static final NexageProjectPage nexageProjectPage = new NexageProjectPage();
    private static final AddProjectForm addProjectForm = new AddProjectForm();

    private UiSteps() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", UiSteps.class.getCanonicalName())); //todo getCanonicalName?
    }

    public static void verifyProjectsPageIsOpen() {
        logger.info(String.format("Checking if %s is open", projectsPage.getName()));
        Assert.assertTrue(projectsPage.state().isDisplayed(), String.format("%s is not open", projectsPage.getName()));
    }

    public static void addCookieWithToken(String token) {
        logger.info(String.format("Adding cookie with previously generated token '%s'", token));
        Cookie cookie = new Cookie(CookieData.TOKEN_PARAMETER, token);
        BrowserActionsUtil.addCookie(cookie);
    }

    public static void refreshPage() {
        BrowserActionsUtil.refresh();
    }

    public static void verifyVariant() {
        logger.info("Checking if variant number in web application footer is correct");
        int actualVariantNumber = ExtractNumberUtil.getVariantNumber(projectsPage.getVersionLabel());
        Assert.assertEquals(actualVariantNumber, TestData.VARIANT_NUMBER, "Variant number is not correct");
    }

    public static void goToNexageProjectPage() {
        logger.info(String.format("Going to %s and checking if it's open", nexageProjectPage.getName()));
        projectsPage.clickNexageButton();
        Assert.assertTrue(nexageProjectPage.state().isDisplayed(), String.format("%s is not open", nexageProjectPage.getName()));
    }

    public static void goBackToProjectsPage() {
        BrowserActionsUtil.goBack();
    }

    public static void addProject(String projectName) {
        openAddProjectForm();
        BrowserActionsUtil.switchToIframe("addProjectFrame"); //todo hardcode
        createNewProject(projectName);
        BrowserActionsUtil.switchToDefaultContent();
    }

    private static void openAddProjectForm() {
        logger.info(String.format("Opening %s and checking if it's open", addProjectForm.getName())); //todo open duplication, decide to use 'checking if' or 'checking that'
        projectsPage.clickAddButton();
        addProjectForm.state().waitForDisplayed();
        Assert.assertTrue(addProjectForm.state().isDisplayed(), String.format("%s is not open", addProjectForm.getName()));
    }

    private static void createNewProject(String projectName) {
        logger.info(String.format("Adding new project '%s' and checking that success message displayed", projectName));
        addProjectForm.inputProjectName(projectName);
        addProjectForm.clickSaveProjectButton();
        Assert.assertTrue(addProjectForm.isSuccessMessageDisplayed(), "'Project saved' message is not displayed");
    }

    public static void verifyProjectAdded(String projectName) {
        logger.info(String.format("Checking that project '%s' added to projects list", projectName));
//        Assert.assertTrue(projectsPage.checkProjectByName(projectName));;
    }
}
