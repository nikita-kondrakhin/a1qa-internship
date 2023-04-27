package steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import constants.JsData;
import org.testng.Assert;
import pages.AddProjectForm;
import utils.BrowserActionsUtil;

public class AddProjectFormSteps {
    private static final Logger logger = AqualityServices.getLogger();
    private static final AddProjectForm addProjectForm = new AddProjectForm();

    private AddProjectFormSteps() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", AddProjectFormSteps.class.getCanonicalName()));
    }

    public static void verifyAddProjectFormIsOpen() {
        addProjectForm.state().waitForDisplayed();
        Assert.assertTrue(addProjectForm.state().isDisplayed(), String.format("%s is not open", addProjectForm.getName()));
    }

    public static void createNewProject(String projectName) {
        logger.info(String.format("Adding new project '%s' and checking that success message displayed", projectName));
        addProjectForm.inputProjectName(projectName);
        addProjectForm.clickSaveProjectButton();
    }

    public static void verifySuccessMessage() {
        Assert.assertTrue(addProjectForm.isSuccessMessageDisplayed(), "'Project saved' message is not displayed");
    }

    public static void closeAddProjectForm() {
        BrowserActionsUtil.executeJavaScript(JsData.CLOSE_POP_UP_METHOD); //todo
    }

    public static void verifyAddProjectFormIsClosed() {
        Assert.assertTrue(addProjectForm.isAddFormClosed(), String.format("%s is open", addProjectForm.getName()));
    }
}
