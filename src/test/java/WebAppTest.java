import constants.TestData;
import models.webapp.WebTableRecord;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;
import steps.*;
import utils.BrowserActionsUtil;
import utils.RandomStringUtil;
import utils.TimeUtil;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WebAppTest extends BaseTest {
    @Test(description = "Verify creation of new project and test record via the web application UI, API and database")
    protected void verifyCreationOfNewProjectAndTestRecord() {
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
        List<WebTableRecord> testsFromPageList = NexageProjectPageSteps.getTestsFromPage();
        List<WebTableRecord> testsFromPageSortedList = testsFromPageList.stream()
                .sorted(Comparator.comparing(WebTableRecord::getLatestTestStartTime).reversed())
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
        ITestResult result = Reporter.getCurrentTestResult();
        String testName = result.getInstanceName();
        String methodName = result.getMethod().getMethodName();
        String testEndTime = TimeUtil.getCurrentTime();
        DatabaseSteps.addTestToDatabase(projectName, testName, methodName, testStartTime, testEndTime);
//        NewProjectPageSteps.verifyNewProjectCreated(projectName);

    }
}
