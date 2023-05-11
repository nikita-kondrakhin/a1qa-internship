package steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import models.webapp.WebTableRecord;
import org.testng.Assert;
import pages.NexageProjectPage;
import utils.BrowserActionsUtil;

import java.util.List;

public class NexageProjectPageSteps {
    private static final Logger logger = AqualityServices.getLogger();
    private static final NexageProjectPage nexageProjectPage = new NexageProjectPage();

    private NexageProjectPageSteps() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", getClass().getSimpleName()));
    }

    public static void verifyNexageProjectPageIsOpen() {
        logger.info(String.format("Checking that %s is open", nexageProjectPage.getName()));
        Assert.assertTrue(nexageProjectPage.state().isDisplayed(), String.format("%s is not open", nexageProjectPage.getName()));
    }

    public static void goBackToProjectsPage() {
        BrowserActionsUtil.goBack();
    }

    public static void verifyWebTableDisplayed() {
        logger.info("Checking that web table is displayed");
        Assert.assertTrue(nexageProjectPage.isWebTableDisplayed(), "Web table is not displayed");
    }

    public static List<WebTableRecord> getTestsFromPage() {
        return nexageProjectPage.getTestsFromWebTable();
    }

    public static void verifyTestsFromPageSortedByDate(List<WebTableRecord> list, List<WebTableRecord> sortedList) {
        logger.info(String.format("Checking that tests on %s are sorted by date in descending order", nexageProjectPage.getName()));
        Assert.assertEquals(list, sortedList, String.format("Tests on %s are not sorted by date in descending order", nexageProjectPage.getName()));
    }

    public static List<String> getTestNamesFromPage() {
        return nexageProjectPage.getTestNamesFromWebTable();
    }

    public static void verifyTestsFromPageMatchesTestsFromDatabase(List<String> listFromPage, List<String> listFromDatabase) {
        Assert.assertTrue(listFromDatabase.containsAll(listFromPage), "Tests records from page are not equal to test records from database");
    }
}
