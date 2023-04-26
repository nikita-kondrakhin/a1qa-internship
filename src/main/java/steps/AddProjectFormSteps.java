package steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.AddProjectForm;

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

    public static void closePopUp() { //todo
//        addProjectForm.closeAddProjectForm("arguments[0].setAttribute('aria-hidden', 'true');");
        JavascriptExecutor jsExecutor = AqualityServices.getBrowser().getDriver();
        WebElement addProjectModal = AqualityServices.getBrowser().getDriver().findElement(By.id("addProject"));
        jsExecutor.executeScript("arguments[0].setAttribute('aria-hidden', 'true');", addProjectModal);

    }

    public static void verifyAddProjectFormIsClosed() {
        Assert.assertFalse(addProjectForm.state().isDisplayed(), String.format("%s is open", addProjectForm.getName()));
    }
}
