import org.testng.annotations.Test;
import steps.ApiSteps;
import steps.UiSteps;

public class WebAppTest extends BaseTest {
    @Test(description = "")
    protected void tempTestName() {
        UiSteps.verifyProjectsPageIsOpen();
        UiSteps.sendCookieWithToken(ApiSteps.getAuthorizationToken());
        UiSteps.refreshPage();
    }
}
