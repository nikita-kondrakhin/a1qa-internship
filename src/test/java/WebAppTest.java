import constants.DataPaths;
import constants.TestData;
import models.database.BaseTable;
import models.database.LogTable;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;
import steps.*;
import utils.BrowserActionsUtil;
import utils.LogUtil;
import utils.RandomStringUtil;
import utils.TimeUtil;
import utils.database.DatabaseQueryUtil;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WebAppTest extends BaseTest {
    @Test(description = "Verify creation of new project and test via the web application UI, API and database")
    protected void webAppTest() {
        String testStartTime = TimeUtil.getCurrentTime();
        ProjectsPageSteps.verifyProjectsPageIsOpen();
        String token = AuthenticationSteps.getToken(TestData.VARIANT_NUMBER);
        AuthenticationSteps.verifyToken();
        AuthenticationSteps.addCookieWithToken(token);
        ProjectsPageSteps.refreshPage();
        ProjectsPageSteps.verifyVariant();
        ProjectsPageSteps.goToNexageProjectPage();
        NexageProjectPageSteps.verifyNexageProjectPageIsOpen();
        NexageProjectPageSteps.verifyWebTableDisplayed();
        List<models.webapp.Test> testsFromPageList = NexageProjectPageSteps.getTestsFromPage();
        List<models.webapp.Test> testsFromPageSortedList = testsFromPageList.stream()
                .sorted(Comparator.comparing(models.webapp.Test::getLatestTestStartTime).reversed())
                .collect(Collectors.toList());
        NexageProjectPageSteps.verifyTestsFromPageSortedByDate(testsFromPageList, testsFromPageSortedList);
        List<String> testNamesFromPageList = NexageProjectPageSteps.getTestNamesFromPage();
        List<String> testNamesFromDatabaseList = DatabaseSteps.getTestNamesFromDatabase();
        NexageProjectPageSteps.verifyTestsFromPageMatchesTestsFromDatabase(testNamesFromPageList, testNamesFromDatabaseList);
        NexageProjectPageSteps.goBackToProjectsPage();
        ProjectsPageSteps.openAddProjectForm();
        AddProjectFormSteps.verifyAddProjectFormIsOpen();
        String projectName = TestData.NEW_PROJECT_NAME + RandomStringUtil.getRandomString(TestData.RANDOM_STRING_LENGTH);
        BrowserActionsUtil.switchToIframe(TestData.IFRAME_ID);
        AddProjectFormSteps.createNewProject(projectName);
        AddProjectFormSteps.verifySuccessMessage();
        BrowserActionsUtil.switchToDefaultContent();
        AddProjectFormSteps.closeAddProjectForm();
        AddProjectFormSteps.verifyAddProjectFormIsClosed();
        ProjectsPageSteps.refreshPage();
        ProjectsPageSteps.verifyProjectAppearedInList(projectName);
        ProjectsPageSteps.openProjectByName(projectName);

//        NewProjectPageSteps.verifyNewProjectPageIsOpen();

        String testName = getClass().getSimpleName();
        String testEndTime = TimeUtil.getCurrentTime();
        String log = LogUtil.getLog();
        DatabaseSteps.addTestToDatabase(testName, testStartTime, testEndTime, log);

    }
}
