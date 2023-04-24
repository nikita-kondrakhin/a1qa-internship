import constants.TestData;
import org.testng.annotations.Test;
import steps.ApiSteps;
import steps.DatabaseSteps;
import steps.UiSteps;

public class WebAppTest extends BaseTest {
    @Test(description = "") //todo
    protected void webAppTest() {
        UiSteps.verifyProjectsPageIsOpen();
        UiSteps.sendCookieWithToken(ApiSteps.getToken(TestData.VARIANT_NUMBER));
        ApiSteps.verifyToken();
        UiSteps.refreshPage();
        UiSteps.verifyVariant();
        UiSteps.goToNexageProjectPage();

        DatabaseSteps.getNexageProjectTestsList();

        UiSteps.goBackToProjectsPage();

    }
}
