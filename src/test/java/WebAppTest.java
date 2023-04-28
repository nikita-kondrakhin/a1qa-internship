import constants.TestData;
import org.testng.IClass;
import org.testng.annotations.Test;
import steps.*;
import utils.BrowserActionsUtil;
import utils.RandomStringUtil;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WebAppTest extends BaseTest {
    @Test(description = "Verify creation of new project and test via the web application UI, API and database")
    protected void webAppTest() {
//        System.out.println(test.getTestName());

        ProjectsPageSteps.verifyProjectsPageIsOpen();
        String token = AuthenticationSteps.getToken(TestData.VARIANT_NUMBER);
        AuthenticationSteps.verifyToken();
        AuthenticationSteps.addCookieWithToken(token);
        ProjectsPageSteps.refreshPage();
        ProjectsPageSteps.verifyVariant();
        ProjectsPageSteps.goToNexageProjectPage();
        NexageProjectPageSteps.verifyNexageProjectPageIsOpen();
        NexageProjectPageSteps.verifyWebTableDisplayed();
        List<models.Test> testsFromPageList = NexageProjectPageSteps.getTestsFromPage();
        List<models.Test> testsFromPageSortedList = testsFromPageList.stream()
                .sorted(Comparator.comparing(models.Test::getLatestTestStartTime).reversed())
                .collect(Collectors.toList());
        NexageProjectPageSteps.verifyTestsFromPageSortedByDate(testsFromPageList, testsFromPageSortedList);
        List<String> testNamesFromPageList = NexageProjectPageSteps.getTestNamesFromPage();
        List<String> testNamesFromDatabaseList = DatabaseSteps.getTestNamesFromDatabase();

        for(int i = 0; i < testsFromPageList.size(); i++) {
            System.out.println("Data from PG: " + testsFromPageSortedList.get(i) + "!!!");
            System.out.println("Name from PG: " + testNamesFromPageList.get(i) + "!!!");
            System.out.println("Data from DB: " + testNamesFromDatabaseList.get(i) + "!!!");
            System.out.println();
        }

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

        String testName = TestData.NEW_TEST_NAME + RandomStringUtil.getRandomString(TestData.RANDOM_STRING_LENGTH);

//        DatabaseSteps.addTestToDatabase(testName, test.getTestName());

    }
}
