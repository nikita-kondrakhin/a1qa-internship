import constants.TestData;
import org.testng.annotations.Test;
import steps.ApiSteps;
import steps.DatabaseSteps;
import steps.UiSteps;
import utils.RandomStringUtil;

public class WebAppTest extends BaseTest {
    @Test(description = "") //todo
    protected void webAppTest() {
        UiSteps.verifyProjectsPageIsOpen();
        UiSteps.addCookieWithToken(ApiSteps.getToken(TestData.VARIANT_NUMBER));
        ApiSteps.verifyToken();
        UiSteps.refreshPage();
        UiSteps.verifyVariant();
        UiSteps.goToNexageProjectPage();

        DatabaseSteps.getNexageProjectTestsList();

        UiSteps.goBackToProjectsPage();
        String projectName = TestData.NEW_PROJECT_NAME + RandomStringUtil.getRandomString(TestData.RANDOM_STRING_LENGTH);
        UiSteps.addProject(projectName);
        UiSteps.verifyProjectAdded(projectName);
    }
}
