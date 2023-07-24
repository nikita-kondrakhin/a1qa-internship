package steps;

import org.testng.Assert;
import pages.NewTestPage;

public class NewTestPageSteps {
    private static final NewTestPage newTestPage = new NewTestPage();

    public static void verifyNewTestPageIsOpened() {
        Assert.assertTrue(newTestPage.state().isDisplayed(), String.format("'%s' is not open", newTestPage.getName()));
    }

    public static void verifyTestData() {

    }

    public static void verifyScreenshot(byte[] screenshot) {
//        Image actualScreenshot = newTestPage.getScreenshot();
//        Image expectedScreenshot = screenshot;
    }

    public static void openScreenshot() {
        newTestPage.openScreenshot();
    }
}
