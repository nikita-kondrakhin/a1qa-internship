import constants.TestData;
import org.testng.annotations.Test;
import steps.*;
import utils.BrowserActionsUtil;
import utils.RandomStringUtil;

public class WebAppTest extends BaseTest {
    @Test(description = "") //todo
    protected void webAppTest() {
        ProjectsPageSteps.verifyProjectsPageIsOpen();
        String token = AuthenticationSteps.getToken(TestData.VARIANT_NUMBER);
        AuthenticationSteps.verifyToken();
        AuthenticationSteps.addCookieWithToken(token);
        ProjectsPageSteps.refreshPage();
        ProjectsPageSteps.verifyVariant();
        ProjectsPageSteps.goToNexageProjectPage();
        NexageProjectPageSteps.verifyNexageProjectPageIsOpen();

        NexageProjectPageSteps.getTestsFromPage();
        DatabaseSteps.getNexageProjectTestsList();

        NexageProjectPageSteps.goBackToProjectsPage();
        ProjectsPageSteps.openAddProjectForm();
        AddProjectFormSteps.verifyAddProjectFormIsOpen();
        String projectName = TestData.NEW_PROJECT_NAME + RandomStringUtil.getRandomString(TestData.RANDOM_STRING_LENGTH);
        BrowserActionsUtil.switchToIframe(TestData.IFRAME_ID);
        AddProjectFormSteps.createNewProject(projectName);
        AddProjectFormSteps.verifySuccessMessage();
        BrowserActionsUtil.switchToDefaultContent();

        AddProjectFormSteps.closePopUp();

        AddProjectFormSteps.verifyAddProjectFormIsClosed();
        ProjectsPageSteps.refreshPage();
        ProjectsPageSteps.verifyProjectAppearedInList(projectName);
        ProjectsPageSteps.openProjectByName(projectName);
    }
}
